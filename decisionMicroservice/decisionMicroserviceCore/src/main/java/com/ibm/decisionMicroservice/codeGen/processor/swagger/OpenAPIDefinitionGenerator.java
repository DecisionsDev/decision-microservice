package com.ibm.decisionMicroservice.codeGen.processor.swagger;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.ibm.decisionMicroservice.codeGen.processor.swagger.trace.DecisionTrace;
import com.ibm.rules.htds.plugin.cci.internal.HTDSSignature.Parameter;
import com.ibm.rules.htds.plugin.cci.internal.HTDSSignature.Parameter.Direction;
import ilog.rules.res.model.IlrPath;
import ilog.rules.res.model.IlrRulesetParameter;
import io.swagger.converter.ModelConverters;
import io.swagger.jackson.ModelResolver;
import io.swagger.models.*;
import io.swagger.models.parameters.BodyParameter;
import io.swagger.models.properties.IntegerProperty;
import io.swagger.models.properties.Property;
import io.swagger.models.properties.RefProperty;
import io.swagger.models.properties.StringProperty;

import javax.xml.bind.annotation.XmlEnumValue;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.*;

public class OpenAPIDefinitionGenerator {
	public static final String BASE = "/";

	public Swagger generateDefinition(List<IlrRulesetParameter> rulesetParameters, String xomPath, String rulesetPath) throws OpenAPIDefinitionGenerationException {

		String host = "";
		String basePath = BASE;
		Locale locale = Locale.ENGLISH;
		OpenAPIUtil.OpenAPIDefinitionFormat openAPIFormat = OpenAPIUtil.OpenAPIDefinitionFormat.JSON;
		boolean enableTrace = false;
		boolean enableAPIConnect = false;
		String protocol = "http";

		// currently, we only support Java XOM 
		// => throw an com.ibm.decisionMicroservice.exception if the signature contains XML parameters
        //        if(signature.getParameters()!=null && DecisionServiceUtil.hasXMLElement(signature.getParameters())) {
		//        	throw new HTTPError(RESTResponse.NOT_IMPLEMENTED, HTDSLocalization.HELPER.getLocalizedMessage(HTDSLocalization.ERROR_OPENAPI_NOT_SUPPORTED_FOR_XML_XOM, locale),
		//        			HTDSLocalization.HELPER.getLocalizedMessage(HTDSLocalization.ERROR_OPENAPI_NOT_SUPPORTED_FOR_XML_XOM, locale));
		//        }


		// retrieving the OpenAPI serializer, depending on the format
		ObjectMapper objectMapper = OpenAPIUtil.getObjectMapper(openAPIFormat);

		// overriding the mechanism the resolves enum properties to handle JAXB and Jackson annotations
		ModelResolver modelResolver = new ModelResolver(objectMapper) {
			@SuppressWarnings("deprecation")
			protected void _addEnumProps(final Class<?> propertyCls, final Property property) {

				@SuppressWarnings("unchecked")
				Class<Enum<?>> enumClass = (Class<Enum<?>>) propertyCls;
				for (Enum<?> enumValueType : enumClass.getEnumConstants()) {
					String retrievedEnumValue = null;
					// if option to use enum indexes is set on mapper,
					// using the enum ordinal as value
					if (_mapper.isEnabled(SerializationFeature.WRITE_ENUMS_USING_INDEX)) {
						retrievedEnumValue = String.valueOf(enumValueType.ordinal());
					}
					// otherwise checking if option to use toString() is set on mapper, and if so,
					// using toString() as value
					else if (_mapper.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)) {
						retrievedEnumValue = enumValueType.toString();
					}
					// otherwise, using the enum value name,
					// unless there is a JAXB annotation or Jackson annotation
					else {
						String rawValue = enumValueType.name();

						// check if enum value is annotated
						// XML annotations
						// -> we should reuse the JaxbAnnotationIntrospector instance,
						// but it is not working properly with Jackson 2.8.8,
						// so here we are manually looking for @XmlEnumValue annotation
						XmlEnumValue annotatedValue = null;
						try {
							annotatedValue = (XmlEnumValue)enumValueType.getDeclaringClass().getDeclaredField(rawValue).getAnnotation(XmlEnumValue.class);
							if(annotatedValue != null) retrievedEnumValue = annotatedValue.value();
						}
						catch (Exception e) {
						}

						// Jackson annotations (e.g @JsonProperty)
						// -> reusing the Jackson annotation introspector
						if(retrievedEnumValue == null){
							try{
								final AnnotationIntrospector jacksonIntrospector = new JacksonAnnotationIntrospector();
								retrievedEnumValue = jacksonIntrospector.findEnumValue(enumValueType);
							}
							catch (Exception e) {
							}
						}

						// in case of an issue, use the enum name
						if(retrievedEnumValue == null){
							retrievedEnumValue = rawValue;
						}
					}
					// setting the value through the model
					if(retrievedEnumValue != null){
						if (property instanceof StringProperty) {
							StringProperty stringProperty = (StringProperty)property;
							stringProperty._enum(retrievedEnumValue);
						}
					}
				}
			}
		};


		// creating the base object that represents the openAPI (swagger) definition in memory
		final Swagger swagger = new Swagger();

		// Currently, we do not support the following fields:
		// - tags
		// - externalDocs

		///////////////
		// Info part //
		///////////////
		// Currently, we do not support the following fields in the info object:
		// - termsOfService
		// - contact
		// - license

		final Info info = new Info();
		info.version(OpenAPIUtil.DEFAULT_API_VERSION);
		String ruleAppName = "ruleapp";
		String rulesetName ="ruleset";
		// setting the info title:
		// the info title is first retrieved in the ruleset archive properties ;
		// if it is not set, a default title is used.
		String infoTitle =  rulesetName+" API";
		info.title(infoTitle);
		// setting the info description:
		// the info description is first retrieved in the ruleset archive properties ;
		// if it is not set, a default description is used.
		String infoDescription = "API to invoke the execution of the decision service operation "+rulesetName+".";
		info.description(infoDescription);

		// setting info onto model
		swagger.info(info);
		///////////////


		//////////////////////////////////////////
		// setting host, base path, and schemes //
		//////////////////////////////////////////
		swagger.setHost(host);
		swagger.setBasePath(basePath);

		List<Scheme> schemes = new ArrayList<Scheme>();
		schemes.add(Scheme.HTTP);
		//////////////////////////////////////////

		////////////////////////////////////////////
		// global 'produces' and 'consumes' types //
		////////////////////////////////////////////
		// for now, only supporting json, because in xml we may have interference with jaxb annotations -> //TODO : to be investigated
		List<String> contentTypes = Arrays.asList(new String[]{ "application/json" });
		swagger.setConsumes(contentTypes);
		swagger.setProduces(contentTypes);
		////////////////////////////////////////////

		////////////////////
		// security stuff //
		////////////////////
		//				if(enableBasicAuth){
		//				SecuritySchemeDefinition basicAuthSecuritySchemeDef = new BasicAuthDefinition();
		//				basicAuthSecuritySchemeDef.setDescription(HTDSLocalization.HELPER.getLocalizedMessage(HTDSLocalization.MSG_BASIC_AUTHENTICATION_DESCRIPTION, locale));
		//				swagger.addSecurityDefinition(OpenAPIUtil.BASIC_AUTH_NAME, basicAuthSecuritySchemeDef);
		//				swagger.addSecurity(new SecurityRequirement().requirement(OpenAPIUtil.BASIC_AUTH_NAME));
		//				}

		/////////////////////////////////////////////////////////////////////////
		// define the unique POST operation representing the ruleset execution //
		/////////////////////////////////////////////////////////////////////////
		final io.swagger.models.parameters.Parameter requestParameter = new BodyParameter()
				.name(OpenAPIUtil.REQUEST_CLASS_NAME)
				.description("Request for the execution of the decision service operation. Contains notably the input parameters that are used for the execution.") //i18n
				.schema(new RefModel().asDefault(OpenAPIUtil.REQUEST_CLASS_NAME));
		requestParameter.setRequired(true);

		final io.swagger.models.Response responseDefinition = new io.swagger.models.Response()
				.description("Response for the execution of the decision service operation. Contains notably the output parameters that are returned by the execution.") //i18n
				.schema(new RefProperty().asDefault(OpenAPIUtil.RESPONSE_CLASS_NAME));

		final io.swagger.models.Response errorResponseDefinition = new io.swagger.models.Response()
				.description("Error occurring when invoking the execution of the decision service operation.")
				.schema(new RefProperty().asDefault(OpenAPIUtil.ERROR_CLASS_NAME));


		// retrieving the summary for the "execute" operation:
		// the "execute" operation summary is first retrieved in the ruleset archive properties ;
		// if it is not set, a default summary is used.
		String executeOperationSummary = "Invokes the execution of the decision service operation "+rulesetName+".";
		// retrieving the description for the "execute" operation:
		// the "execute" operation description is first retrieved in the ruleset archive properties ;
		// if it is not set, a default description is used.
		String executeOperationDescription = "Executes the decision service operation "+rulesetName+" with the path "+rulesetPath+".";
		// retrieving the id for the "execute" operation:
		// the "execute" operation id is first retrieved in the ruleset archive properties ;
		// if it is not set, a default value is used.
		// NB: This id MUST be unique among all operations described in the API.
		String executeOperationId = String.format("call%sDecisionServiceOperation", rulesetName);

		// declare the path corresponding to the ruleset execution operation
		final Operation executeOperation = new Operation()
				.summary(executeOperationSummary)
				.description(executeOperationDescription)
				.operationId(executeOperationId)
				.parameter(requestParameter)
				.response(200, responseDefinition)
				.defaultResponse(errorResponseDefinition);

		// declare the operation as a POST operation
		swagger.path(rulesetPath, new Path().post(executeOperation));
		/////////////////////////////////////////////////////////////////////////

		// base Request class
		ModelImpl requestModel = new ModelImpl()
				.name(OpenAPIUtil.REQUEST_CLASS_NAME)
				.type("object")
				.description("Request for the execution of the decision service operation. Contains notably the input parameters that are used for the execution.") //i18n
				.property(OpenAPIUtil.DECISION_ID_PROPERTY,
						new StringProperty().description("Unique identifier representing the execution of the decision service operation. If it is not specified, it will be computed automatically.")); //u18n
		swagger.model(OpenAPIUtil.REQUEST_CLASS_NAME, requestModel);

		// add the trace filter in the Request and declare the type if the trace should be enabled
		if (enableTrace) {
			// adding the trace filter property to the Request model
			Property traceFilterProperty = ModelConverters.getInstance().readAsProperty(ExtendedTraceFilter.class);
			requestModel.property(OpenAPIUtil.TRACE_FILTER_PROPERTY, traceFilterProperty);
			// declaring the trace filter type definition and all the dependencies
			final Map<String, Model> modelTraceFilterAndDeps = ModelConverters.getInstance().readAll(ExtendedTraceFilter.class);
			for(Map.Entry<String, Model> model : modelTraceFilterAndDeps.entrySet()){
				if (model.getKey().equals("TraceFilter")) {
					model.getValue().setDescription("Trace filter for the execution of the decision service operation. Allows to determine the execution details that should be extracted and returned in the response."); //i18n
				}
				swagger.model(model.getKey(), model.getValue());
			}
		}

		// base Response class
		ModelImpl responseModel = new ModelImpl()
				.name(OpenAPIUtil.RESPONSE_CLASS_NAME)
				.type("object")
				.description("Response for the execution of the decision service operation. Contains notably the output parameters that are returned by the execution.") //i18n
				.property(OpenAPIUtil.DECISION_ID_PROPERTY,
						new StringProperty().description("Unique identifier representing the execution of the decision service operation. If it is not specified, it will be computed automatically.") //i18n
				);
		swagger.model(OpenAPIUtil.RESPONSE_CLASS_NAME, responseModel);

		// add the trace in the Response and declare the required types if the trace should be enabled
		if (enableTrace) {
			// adding the trace property to the Response model
			Property traceProperty = ModelConverters.getInstance().readAsProperty(DecisionTrace.class);
			responseModel.property(OpenAPIUtil.TRACE_PROPERTY, traceProperty);
			// declaring the trace type definition and all the dependencies
			final Map<String, Model> modelTraceAndDeps = ModelConverters.getInstance().readAll(DecisionTrace.class);
			for(Map.Entry<String, Model> model : modelTraceAndDeps.entrySet()){
				if (model.getKey().equals("decisionTrace")) {
					model.getValue().setDescription("Trace for the execution of the decision service operation. Contains execution details. The information returned in the trace depends on the trace filter set in the request."); //i18n
				}
				swagger.model(model.getKey(), model.getValue());
			}
		}

		// error type declaration
		ModelImpl errorModel = new ModelImpl()
				.name(OpenAPIUtil.ERROR_CLASS_NAME)
				.type("object")
				.description("Error occurring when invoking the execution of the decision service operation.")
				.property(OpenAPIUtil.ERROR_HTTP_CODE_PROPERTY, new IntegerProperty()
						.description("HTTP error code."))
				.property(OpenAPIUtil.ERROR_MESSAGE_PROPERTY, new StringProperty()
						.description("Error message."))
				.property(OpenAPIUtil.ERROR_DETAILS_PROPERTY, new StringProperty()
						.description("Detailed error message."))
				.property(OpenAPIUtil.ERROR_ERROR_CODE_PROPERTY, new StringProperty()
						.description("Product error code."));

		swagger.model(OpenAPIUtil.ERROR_CLASS_NAME, errorModel);

		URL[] urls = new URL[1];
		try {
			urls[0] = Paths.get(xomPath).toUri().toURL();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
		ClassLoader managedXOMClassLoader =new URLClassLoader(urls);

		// iterating over signature:
		// IN parameter -> added to Request
		// INOUT parameter -> added to Request + Response
		// OUT parameter -> added to Response
		TreeSet<Parameter> parameters = getRulesetParametersAsTreeSet(rulesetParameters);
		for(Iterator<Parameter> iterator = parameters.iterator(); iterator.hasNext();) {
			Parameter parameter = (Parameter) iterator.next();
			boolean addedToRequest = parameter.getDirection()== Direction.IN || parameter.getDirection()== Direction.INOUT;
			boolean addedToResponse = parameter.getDirection()== Direction.OUT || parameter.getDirection()== Direction.INOUT;

			String parameterType = parameter.getType();
			Class<?> parameterClass = loadType(parameterType, managedXOMClassLoader);

			Property addedProperty = ModelConverters.getInstance().readAsProperty(parameterClass);

			// completing Request/Response
			if(addedProperty != null){
				if(addedToRequest) requestModel.property(parameter.getName(), addedProperty);
				if(addedToResponse) responseModel.property(parameter.getName(), addedProperty);
			}

			// declaring signature type definitions
			final Map<String, Model> modelRsParameterAndDeps = ModelConverters.getInstance().readAll(parameterClass);
			for(Map.Entry<String, Model> modelParam : modelRsParameterAndDeps.entrySet()){
				swagger.model(modelParam.getKey(), modelParam.getValue());
			}
		}

		// Apply the API Connect extension if required
		if (enableAPIConnect) OpenAPIUtil.applyAPIConnectExtension(swagger, protocol, new IlrPath(rulesetPath), locale);

		return swagger;
	}

	private Class<?> loadType(String typeToLoad, ClassLoader managedXOMClassLoader) throws OpenAPIDefinitionGenerationException{
		Class<?> parameterClass = null;

		// special case for RS parameters that are java primitive types
		parameterClass = OpenAPIUtil.getClassForPrimitiveType(typeToLoad);
		if(parameterClass != null) return parameterClass;

		// special case for RS parameters that are arrays
		if(typeToLoad.endsWith("[]")){
			// "fixing" parameter type
			typeToLoad = OpenAPIUtil.convertArrayTypeNameToJVMCompliantName(typeToLoad);
		}

		// loading class
		// using Class.forName(...) instead of <classloader>.loadClass(...) to avoid issues on some JVMs/appservers
		// (typically, getClass().getClassLoader().loadClass("[Ljava.lang.String;") does not work on WLP)
		try{
			// trying to load class with managedXOM classloader if available (e.g., if there is a xom)
			if(managedXOMClassLoader != null){
				try{
					parameterClass = Class.forName(typeToLoad, false, managedXOMClassLoader);
				}
				catch(Exception ex){
//					if (logger.isLoggable(Level.FINER)) {
//			            logger.log(Level.FINER, "Type '"+typeToLoad+"' could not be loaded with the managed XOM classloader: "+ex.getMessage(), null, null);
//			        }
				}
			}

			// if the managedXOM classloader is not available, 
			// or if loading the class with it did not succeed,
			// we then try using the current classloader
			if(parameterClass == null){
				parameterClass = Class.forName(typeToLoad, false, null); // passing null as classloader will force use current classloader
			}
		}
		catch(Exception e){
			throw new OpenAPIDefinitionGenerationException("Could not resolve class for type '"+typeToLoad+"'.", e);
		}

		// this is not supposed to happen, if the class cannot be loaded, an com.ibm.decisionMicroservice.exception should be raised
		if(parameterClass == null) throw new OpenAPIDefinitionGenerationException("Could not resolve class for type '"+typeToLoad+"'.");

		return parameterClass;
	}

	private static ClassLoader getXOMClassloader() {
		throw new RuntimeException();
	}

	private TreeSet<Parameter> getRulesetParametersAsTreeSet(List<IlrRulesetParameter> rulesetParameters){
		TreeSet<Parameter> parameters = new TreeSet<>();

		for(IlrRulesetParameter param: rulesetParameters) {

			Direction direction = Direction.OUT;
			if(param.getDirection() == IlrRulesetParameter.DIRECTION_IN) {
				direction = Direction.IN;
			}
			else if(param.getDirection() == IlrRulesetParameter.DIRECTION_INOUT) {
				direction = Direction.INOUT;
			}


			parameters.add(new Parameter(direction, param.getName(), "", param.getType()));
		}

		return parameters;
	}

	protected class OpenAPIDefinitionGenerationException extends Exception{
		private static final long serialVersionUID = 1L;

		public OpenAPIDefinitionGenerationException(String message){
			super(message);
		}

		public OpenAPIDefinitionGenerationException(Exception rootCause){
			super(rootCause);
		}

		public OpenAPIDefinitionGenerationException(String message, Exception rootCause){
			super(message, rootCause);
		}
	}
}

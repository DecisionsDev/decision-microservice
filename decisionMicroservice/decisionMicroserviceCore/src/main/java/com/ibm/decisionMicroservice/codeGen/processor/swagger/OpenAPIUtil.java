/*
 *
 * IBM Confidential
 * OCO Source Materials
 * 5724X98 5724Y15 5655V82 5724X99 5724Y16 5655V89 5725B69 5655W88 5725C52 5655W90 5655Y31
 * Copyright IBM Corp. 1987, 2019
 * The source code for this program is not published or other-
 * wise divested of its trade secrets, irrespective of what has
 * been deposited with the U.S Copyright Office.
 *
 */
package com.ibm.decisionMicroservice.codeGen.processor.swagger;

import com.fasterxml.jackson.databind.ObjectMapper;
import ilog.rules.res.model.IlrPath;
import io.swagger.models.Swagger;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Helper tool for OpenAPI (Swagger) definition file generation
 */
public class OpenAPIUtil {
	
	public final static String DEFAULT_API_VERSION = "1.0.0";
	public final static String BASIC_AUTH_NAME = "basicAuthentication";
	public final static String REQUEST_CLASS_NAME = "Request";
	public final static String RESPONSE_CLASS_NAME = "Response";
	public final static String ERROR_CLASS_NAME = "Error";
	public final static String DECISION_ID_PROPERTY = "__DecisionID__";
	public final static String TRACE_FILTER_PROPERTY = "__TraceFilter__";
	public final static String TRACE_PROPERTY = "__decisionTrace__";
	public final static String ERROR_HTTP_CODE_PROPERTY = "code";
	public final static String ERROR_ERROR_CODE_PROPERTY = "errorCode";
	public final static String ERROR_DETAILS_PROPERTY = "details";
	public final static String ERROR_MESSAGE_PROPERTY = "message";
	public final static String DEFAULT_EXECUTE_OPERATION_ID_FORMAT = "call%sDecisionServiceOperation";
	
	// Ruleset archive properties used to parameterize the OpenAPI definition file generation
	public final static String INFO_TITLE_PROPERTY = "openapi.info.title";
	public final static String INFO_DESCRIPTION_PROPERTY = "openapi.info.description";
	public final static String EXECUTE_OPERATION_SUMMARY_PROPERTY = "openapi.execute.operation.summary";
	public final static String EXECUTE_OPERATION_DESCRIPTION_PROPERTY = "openapi.execute.operation.description";
	public final static String EXECUTE_OPERATION_ID_PROPERTY = "openapi.execute.operation.operationId";
	
	// Additional properties for API Connect
	// public final static String API_KEY_AUTH_NAME = "clientID";
	
	public static enum OpenAPIDefinitionFormat { 
		YAML("text/plain", "application/yaml", ".yaml"), JSON("application/json", "application/json", ".json");
		
		private String contentTypeForDisplay;
		private String contentTypeForDownload;
		private String fileExtension;
		
		public String getContentTypeForDisplay(){
			return contentTypeForDisplay;
		}
		
		public String getContentTypeForDownload(){
			return contentTypeForDownload;
		}
		
		public String getFileExtension(){
			return fileExtension;
		}
		
		OpenAPIDefinitionFormat(String contentTypeForDisplay, String contentTypeForDownload, String fileExtension){
			this.contentTypeForDisplay = contentTypeForDisplay;
			this.contentTypeForDownload = contentTypeForDownload;
			this.fileExtension = fileExtension;
		}
	};
	
	public static Map<String, Class<?>> primitiveTypesClasses = new HashMap<String, Class<?>>();
	
	static{
		primitiveTypesClasses.put("boolean", boolean.class);
		primitiveTypesClasses.put("byte", byte.class);
		primitiveTypesClasses.put("char", char.class);
		primitiveTypesClasses.put("double", double.class);
		primitiveTypesClasses.put("float", float.class);
		primitiveTypesClasses.put("int", int.class);
		primitiveTypesClasses.put("long", long.class);
		primitiveTypesClasses.put("short", short.class);
	}
	
	public static ObjectMapper getObjectMapper(OpenAPIDefinitionFormat format){
		// create a mapper with support for JAXB annotations (XmlTransient, XmlElement, ...), even when in json
		ObjectMapper mapper = new ObjectMapper();
		return mapper;
	}
	
	// when retrieving ruleset parameter types, we get a string representing this type
    // and when it is an array type, it is expressed with a custom formalism
    // and not with JVM standards
    // for example, a string array will appear as java.lang.String[], whereas the JVM expects [Ljava.lang.String;
    public static String convertArrayTypeNameToJVMCompliantName(String typeName){
    	if(typeName == null) return null;
    	if(! typeName.endsWith("[]")) return typeName;
    	
    		// gets the base FQN (e.g java.lang.String[] -> java.lang.String[]
			String baseFQN = typeName.substring(0, typeName.indexOf("[]"));
			// gets the array dimension (e.g int[][][] -> 3)
			int dimension = StringUtils.countMatches(typeName.substring(typeName.indexOf("[]")), "[]");
			// building a compliant JVM type
			String fixedParameterType = "";
			for(int p=0;p<dimension;p++){
				fixedParameterType += "[";
			}
			
			if("byte".equals(baseFQN.toLowerCase())){
				fixedParameterType += "B";
			}
			else if("char".equals(baseFQN.toLowerCase())){
				fixedParameterType += "C";
			}
			else if("double".equals(baseFQN.toLowerCase())){
				fixedParameterType += "D";
			}
			else if("float".equals(baseFQN.toLowerCase())){
				fixedParameterType += "F";
			}
			else if("int".equals(baseFQN.toLowerCase())){
				fixedParameterType += "I";
			}
			else if("long".equals(baseFQN.toLowerCase())){
				fixedParameterType += "J";
			}
			else if("short".equals(baseFQN.toLowerCase())){
				fixedParameterType += "S";
			}
			else if("boolean".equals(baseFQN.toLowerCase())){
				fixedParameterType += "Z";
			}
			else{
				fixedParameterType += "L";
				fixedParameterType += baseFQN;
				fixedParameterType += ";";
			}
			
			return fixedParameterType;
    }
    
    public static Class<?> getClassForPrimitiveType(String className) {
    	if(primitiveTypesClasses.containsKey(className)){
    		return primitiveTypesClasses.get(className);
    	}
    	
    	return null;
    }
    
    public static void applyAPIConnectExtension(Swagger swagger, String protocol, IlrPath rulesetPath, Locale locale) {
		// Retrieve the target URL to invoke the execution REST API
		String targetURL = protocol + "://" + swagger.getHost() + swagger.getBasePath() + rulesetPath.toString();
		// Set the host for API Connect
		swagger.setHost("$(catalog.host)");
		// Reset the schemes
		swagger.setSchemes(null);
		// Reset the security
		swagger.setSecurityDefinitions(null);
		swagger.setSecurity(null);
		
		// Create a policy to invoke the execution REST API
		final Map<String, Object> invokePolicy = new HashMap<String, Object>();
		invokePolicy.put("target-url", targetURL);
		String title = "Calls Decision Service Operation"; //i18n
		invokePolicy.put("title", title);
		String description = "Executes the decision service operation "+rulesetPath.getRulesetName()+"."; //i18n
		invokePolicy.put("description", description);
		final Map<String, Object> policyMap = new HashMap<String, Object>();
		policyMap.put("invoke", invokePolicy);
		// Create a list of policies and add the invoke policy in the list
		final List<Map<String, Object>> policies = new ArrayList<Map<String, Object>>();
		policies.add(policyMap);
		// Create an assembly and add the list of policies to the assembly
		final Map<String, Object> assemblyMap = new HashMap<String, Object>();
		assemblyMap.put("execute", policies);
		// Create a configuration and add the assembly to the configuration
		final Map<String, Object> configurationMap = new HashMap<String, Object>();
        configurationMap.put("assembly", assemblyMap);
        // Set the configuration as vendor extension
		swagger.setVendorExtension("x-ibm-configuration", configurationMap);
		
		// We do not add a security by default. We let the customer set it in API Connect.
		/*
		// Add API Key Authentication definition (which is the default for API Connect).
		SecuritySchemeDefinition apiKeySecuritySchemeDef = new ApiKeyAuthDefinition("X-IBM-Client-Id", In.HEADER);
		apiKeySecuritySchemeDef.setDescription("");
		swagger.addSecurityDefinition(OpenAPIUtil.API_KEY_AUTH_NAME, apiKeySecuritySchemeDef);
		swagger.addSecurity(new SecurityRequirement().requirement(OpenAPIUtil.API_KEY_AUTH_NAME));
		*/
	}
    
}

package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.AdditionalProperties;
import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.codeGen.processor.swagger.OpenAPIDefinitionGenerator;
import com.ibm.decisionMicroservice.ruleSet.RuleSetParser;
import io.swagger.models.Swagger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// generate swagger file from ruleset
public class SwaggerGeneratorProcessor implements Processor {
    private GeneratorOptions options;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void setOptions(GeneratorOptions options) {
        this.options = options;
    }

    @Override
    public void process() {
        try {
            if(this.options.getSwagger() !=null
                    || this.options.getRuleSetPathArchive() == null
                    || this.options.getXomJarPath() == null
                    || (this.options.getRuleSetPath() == null && !this.options.getAdditionalProperties().containsKey(AdditionalProperties.SERVICE_PATH))){
                logger.info("Open api spec not generated : specification already present or missing parameters");
                return;
            }

            RuleSetParser parser = new RuleSetParser(this.options.getRuleSetPathArchive());
            OpenAPIDefinitionGenerator generator = new OpenAPIDefinitionGenerator();

            String servicePath = this.options.getRuleSetPath();
            if(this.options.getAdditionalProperties().containsKey(AdditionalProperties.SERVICE_PATH)){
                servicePath = this.options.getAdditionalProperties().get(AdditionalProperties.SERVICE_PATH);
            }

            Swagger generatedSwagger = generator.generateDefinition(
                    parser.getParameters(),
                    this.options.getXomJarPath(),
                    servicePath
            );

            this.options.setSwagger(generatedSwagger);
            logger.info("open api spec generated");
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}

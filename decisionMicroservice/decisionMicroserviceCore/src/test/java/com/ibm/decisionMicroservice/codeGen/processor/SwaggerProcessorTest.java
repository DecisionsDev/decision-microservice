package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.AdditionalProperties;
import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SwaggerProcessorTest {
    private Processor swaggerProcessor;
    private GeneratorOptions options;

    @Before
    public void init(){
        this.swaggerProcessor = new SwaggerGeneratorProcessor();
        options = new GeneratorOptions();
        swaggerProcessor.setOptions(options);

        String xom = this.getClass().getResource("/Shipment_Pricing-decisionmodel-xom.jar").getFile();
        String ruleset = this.getClass().getResource("/ruleset.dsar").getFile();

        options.setXomJarPath(xom);
        options.setRuleSetPathArchive(ruleset);
        options.getAdditionalProperties().put(AdditionalProperties.SERVICE_PATH,"/test");

    }

    @Test
    public void generationSwaggerTest(){
        this.swaggerProcessor.process();
        assertNotNull(this.options.getSwagger());
    }

    @Test
    public void specificPathTest(){
        this.options.getAdditionalProperties().put(AdditionalProperties.SERVICE_PATH,"/test/test");
        this.swaggerProcessor.process();
        assertTrue(this.options.getSwagger().getPaths().containsKey("/test/test"));
    }

    @Test
    public void emptyOption(){
        this.swaggerProcessor.setOptions(new GeneratorOptions());
        this.swaggerProcessor.process();
    }
}

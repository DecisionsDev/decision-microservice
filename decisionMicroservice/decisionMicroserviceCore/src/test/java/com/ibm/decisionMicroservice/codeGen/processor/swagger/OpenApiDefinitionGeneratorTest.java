package com.ibm.decisionMicroservice.codeGen.processor.swagger;

import io.swagger.models.Swagger;
import org.junit.Test;
import com.ibm.decisionMicroservice.ruleSet.RuleSetParser;

import static org.junit.Assert.*;

public class OpenApiDefinitionGeneratorTest {

    @Test
    public void generationTest() throws Exception{
        String rulesetPath = this.getClass().getResource("/ruleset.jar").getFile();
        String xomPath = this.getClass().getResource("/miniloan-xom.jar").getFile();
        String testPath = "/test/path";

        RuleSetParser parser = new RuleSetParser(rulesetPath);
        OpenAPIDefinitionGenerator generator = new OpenAPIDefinitionGenerator();
        Swagger swagger = generator.generateDefinition(parser.getParameters(),xomPath,testPath);

        assertTrue(swagger.getDefinitions().containsKey("Request"));
        assertTrue(swagger.getDefinitions().containsKey("Response"));

        assertEquals(1,swagger.getPaths().size());
        assertTrue(swagger.getPaths().containsKey(testPath));
        assertNotNull(swagger.getPaths().get(testPath).getPost());
    }

    @Test(expected = OpenAPIDefinitionGenerator.OpenAPIDefinitionGenerationException.class)
    public void xomAndParametersMismatchTest() throws Exception{
        String rulesetPath = this.getClass().getResource("/ruleset.dsar").getFile();
        String xomPath = this.getClass().getResource("/miniloan-xom.jar").getFile();
        String testPath = "/test/path";

        RuleSetParser parser = new RuleSetParser(rulesetPath);
        OpenAPIDefinitionGenerator generator = new OpenAPIDefinitionGenerator();
        Swagger swagger = generator.generateDefinition(parser.getParameters(),xomPath,testPath);
    }


}

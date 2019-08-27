package com.ibm.decisionMicroservice.codeGen.configuration;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RuleprojectConfigurationTest {
    private GeneratorOptions options;
    private Configuration configuration;

    @Before
    public void init(){
        this.options = new GeneratorOptions();
        this.configuration = new RuleprojectConfiguration();
    }

    @Test
    public void fitTest(){
        options.setRuleAppDeploymentName("");
        options.setRuleAppProjectPath("");
        options.setXomJarPath("");
        options.setRuleSetPath("");

        assertTrue(configuration.isCorrect(options));
    }

    @Test
    public void incorrectTest(){
        assertFalse(configuration.isCorrect(this.options));

        this.options.setXomJarPath("");
        assertFalse(configuration.isCorrect(this.options));

        this.options.setRuleSetPath("");
        assertFalse(configuration.isCorrect(this.options));

        this.options.setRuleAppProjectPath("");
        assertFalse(configuration.isCorrect(this.options));

        this.options.setRuleAppDeploymentName("");
        assertTrue(configuration.isCorrect(this.options));
    }

    @Test
    public void notnullListTest(){
        assertNotNull(this.configuration.getProcessors());
    }
}

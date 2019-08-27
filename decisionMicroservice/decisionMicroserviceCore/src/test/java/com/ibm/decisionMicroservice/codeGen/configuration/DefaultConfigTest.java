package com.ibm.decisionMicroservice.codeGen.configuration;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DefaultConfigTest {
    private GeneratorOptions options;
    private DefaultConfigurator configurator;

    @Before
    public void init(){
        this.options = new GeneratorOptions();
        this.configurator = new DefaultConfigurator();
    }

    @Test
    public void noConfigTest(){
        assertFalse(this.configurator.getConfig(this.options).isPresent());
    }

    @Test
    public void getRulesetConfigTest(){
        this.options.setXomJarPath("");
        this.options.setRuleSetPathArchive("");

        assertTrue(this.configurator.getConfig(this.options).isPresent());
    }

    @Test
    public void getRuleappConfigTest(){
        this.options.setXomJarPath("");
        this.options.setRuleSetPath("");
        this.options.setRuleAppJarPath("");

        assertTrue(this.configurator.getConfig(this.options).isPresent());
    }

    @Test
    public void getRuleprojectConfigTest(){
        this.options.setXomJarPath("");
        this.options.setRuleSetPath("");
        this.options.setRuleAppDeploymentName("");
        this.options.setRuleAppProjectPath("");

        assertTrue(this.configurator.getConfig(this.options).isPresent());
    }
}

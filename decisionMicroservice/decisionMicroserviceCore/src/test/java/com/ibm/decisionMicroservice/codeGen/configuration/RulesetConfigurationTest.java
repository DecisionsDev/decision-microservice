package com.ibm.decisionMicroservice.codeGen.configuration;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RulesetConfigurationTest {
    private GeneratorOptions options;
    private Configuration configuration;

    @Before
    public void init(){
        this.options = new GeneratorOptions();
        this.configuration = new RulesetConfiguration();
    }

    @Test
    public void fitTest(){
        options.setRuleSetPathArchive("");
        options.setXomJarPath("");

        assertTrue(configuration.isCorrect(options));
    }

    @Test
    public void incorrectTest(){
        assertFalse(configuration.isCorrect(this.options));

        this.options.setXomJarPath("");
        assertFalse(configuration.isCorrect(this.options));

        this.options.setRuleSetPathArchive("");
        options.setXomJarPath(null);
        assertFalse(configuration.isCorrect(this.options));
    }

    @Test
    public void notnullListTest(){
        assertNotNull(this.configuration.getProcessors());
    }
}

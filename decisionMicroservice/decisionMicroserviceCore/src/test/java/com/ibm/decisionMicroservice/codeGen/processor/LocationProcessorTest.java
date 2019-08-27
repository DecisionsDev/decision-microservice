package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LocationProcessorTest {
    private Processor processor;
    private GeneratorOptions options;

    @Before
    public void init(){
        this.processor = new LocationProcessor();
        this.options = new GeneratorOptions();
        this.processor.setOptions(this.options);
    }

    @Test
    public void relativizeTest(){
        this.options.setOutputDir("/output");
        this.options.setRuleAppJarPath("/output/ruleapp");
        this.options.setXomJarPath("/output/xom");

        processor.process();

        assertEquals(this.options.getRuleAppJarPath(),"ruleapp");
        assertEquals(this.options.getXomJarPath(),"xom");
    }
}

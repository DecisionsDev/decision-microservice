package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DecisionIdProcessorTest {
    private GeneratorOptions options;
    private Processor processor;

    @Before
    public void init(){
        this.options = new GeneratorOptions();
        this.processor = new DecisionIdProcessor();
        this.processor.setOptions(this.options);
    }

    @Test
    public void processTest(){
        this.processor.process();

        assertEquals(1,this.options.getOutputs().size());
        assertEquals(this.options.getInputs(),this.options.getOutputs());
    }
}

package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.exception.MissingOptionException;
import com.ibm.decisionMicroservice.exception.RuleSetReadException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MappingProcessorTest {
    private Processor processor;
    private GeneratorOptions options;

    @Before
    public void init(){
        processor = new MappingProcessor();
        options = new GeneratorOptions();
        processor.setOptions(options);
    }

    @Test
    public void mappingTest() throws Exception{
        String rulesetPath = this.getClass().getResource("/ruleset.dsar").getPath();
        options.setRuleSetPathArchive(rulesetPath);

        processor.process();

        Map<String,String> mapping = new HashMap<>();
        mapping.put("Customer","myModel.CustomerStatus");
        mapping.put("Price","java.lang.Double");
        mapping.put("CustomerStatus","myModel.CustomerStatus");
        mapping.put("Shipment","myModel.odm.Shipment");
        mapping.put("Method","myModel.odm.Method");
        mapping.put("Double","java.lang.Double");
        mapping.put("Distance","java.lang.Double");
        assertEquals(mapping,options.getXomClassMapping());
    }

    @Test (expected = RuleSetReadException.class)
    public void nullFileTest(){
        options.setRuleSetPathArchive("null");
        processor.process();
    }

    @Test (expected = RuleSetReadException.class)
    public void noFileTest(){
        options.setRuleSetPathArchive("azeazer");
        processor.process();
    }

    @Test
    public void emptyOption(){
        this.processor.setOptions(new GeneratorOptions());
        this.processor.process();
    }
}

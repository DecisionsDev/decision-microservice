package com.ibm.decisionMicroservice.codeGen.language.micronaut;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.codeGen.parameters.Directions;
import com.ibm.decisionMicroservice.codeGen.parameters.Parameter;
import com.ibm.decisionMicroservice.codeGen.parameters.ParameterTest;
import com.ibm.decisionMicroservice.codeGen.processor.DefaultValueProcessor;
import com.ibm.decisionMicroservice.codeGen.processor.Processor;
import com.ibm.decisionMicroservice.utils.FileUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class MicronautGeneratorTest {

    private MicronautGenerator generator;
    private GeneratorOptions options;

    @Before
    public void init(){
        generator = new MicronautGenerator();
        options = new GeneratorOptions();

        generator.setOptions(this.options);

        Parameter param = new Parameter("test","String", Directions.INOUT);

        options.setInputs(Arrays.asList(param));
        options.setOutputs(Arrays.asList(param));
        options.setRuleSetPath("test");
        options.setRuleAppJarPath("test");
        options.setXomJarPath("test");
        options.setOutputDir("./test/test");
        Processor defaultProcessor = new DefaultValueProcessor();
        defaultProcessor.setOptions(options);
        defaultProcessor.process();
    }

    @After
    public void cleanup(){
        FileUtil.deleteFolder("./test");
    }

    @Test
    public void generationTest(){
        generator.generate();
        assertTrue(FileUtil.checkFile("./test/test/pom.xml"));
    }
}

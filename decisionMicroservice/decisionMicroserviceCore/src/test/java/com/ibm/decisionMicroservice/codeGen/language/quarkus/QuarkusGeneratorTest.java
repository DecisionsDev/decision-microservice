package com.ibm.decisionMicroservice.codeGen.language.quarkus;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.codeGen.parameters.Directions;
import com.ibm.decisionMicroservice.codeGen.parameters.Parameter;
import com.ibm.decisionMicroservice.codeGen.processor.DefaultValueProcessor;
import com.ibm.decisionMicroservice.codeGen.processor.Processor;
import com.ibm.decisionMicroservice.utils.FileUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class QuarkusGeneratorTest {

    private QuarkusGenerator generator;
    private GeneratorOptions options;

    @Before
    public void init(){
        generator = new QuarkusGenerator();
        options = new GeneratorOptions();
        generator.setOptions(this.options);

        //Parameter param = new Parameter("test","String", Directions.INOUT);
        Parameter param = new Parameter("loan","miniloan.Loan", Directions.INOUT);
        List<Parameter> list = new ArrayList<Parameter>();
        list.add(new Parameter("loan","miniloan.Loan", Directions.INOUT));
        list.add(new Parameter("borrower","miniloan.Borrower", Directions.IN));

        options.setRuleAppDeploymentName("/dkdkdkf/Qsdfqsd");
        options.setInputs(list);
        options.setOutputs(Arrays.asList(param));
        options.setRuleSetPath("/miniloanRulesFromTest/miniloanRulesFromTest");
        options.setRuleAppJarPath("setRuleAppJarPath");
        options.setXomJarPath("setXomJarPath");
        options.setOutputDir("./test/test");
        Processor defaultProcessor = new DefaultValueProcessor();
        defaultProcessor.setOptions(options);
        defaultProcessor.process();
    }

   // @After
   // public void cleanup(){
   //     FileUtil.deleteFolder("./test");
    //}

    @Test
    public void generationTest(){
        generator.generate();
        assertTrue(FileUtil.checkFile("./test/test/pom.xml"));
    }
}

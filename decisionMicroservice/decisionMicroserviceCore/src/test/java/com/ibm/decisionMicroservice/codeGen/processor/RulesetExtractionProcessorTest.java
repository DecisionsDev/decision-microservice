package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.exception.MissingOptionException;
import com.ibm.decisionMicroservice.exception.NoSuchFileException;
import com.ibm.decisionMicroservice.exception.WrongRulesetPathException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.ibm.decisionMicroservice.ruleSet.RuleSetParser;
import com.ibm.decisionMicroservice.utils.FileUtil;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class RulesetExtractionProcessorTest {
    private GeneratorOptions options;
    private RulesetExtractionProcessor processor;

    @Before
    public void init(){
        String ruleAppPath = this.getClass().getResource("/ruleApp_test_deployment_1.0.jar").getPath();
        this.options = new GeneratorOptions();
        this.options.setRuleAppJarPath(ruleAppPath);
        options.setRuleSetPath("test_deployment/1.0/loan_validation_production/1.0");
        processor = new RulesetExtractionProcessor();
        processor.setOptions(this.options);
    }

    @After
    public void cleanup(){
        FileUtil.deleteFolder(this.options.getOutputDir());
    }

    @Test
    public void exctractTest() throws Exception{

        processor.process();

        RuleSetParser parser = new RuleSetParser(this.options.getRuleSetPathArchive());
        Assert.assertNotNull(parser.getRuleSetParametersMapping());
    }

    @Test(expected = NoSuchFileException.class)
    public void noSuchRuleAppTest(){
        this.options.setRuleAppJarPath("tttttt");
        processor.process();
    }

    @Test (expected = WrongRulesetPathException.class)
    public void noSuchRulesetPathTest(){
        this.options.setRuleSetPath("azeazaz");
        processor.process();
    }

    @Test
    public void overwriteExistingFileTest() throws Exception{
        FileUtil.createFile(this.options.getOutputDir() + File.separator + RulesetExtractionProcessor.RULESET_NAME);
        options.setRuleSetPath("test_deployment/1.0/loan_validation_production/1.0");
        processor.process();

        RuleSetParser parser = new RuleSetParser(this.options.getRuleSetPathArchive());
        Assert.assertNotNull(parser.getRuleSetParametersMapping());
    }

    @Test(expected = WrongRulesetPathException.class)
    public void wrongRuleappJarTest(){
        String path = this.getClass().getResource("/miniloan-xom.jar").getPath();
        this.options.setRuleAppJarPath(path);
        this.options.setRuleSetPath("test_deployment/1.0/loan_validation_production/1.0");

        processor.process();
    }

    @Test
    public void slashedRulesetPathTest(){
        options.setRuleSetPath("/" + options.getRuleSetPath());

        processor.process();

        RuleSetParser parser = new RuleSetParser(this.options.getRuleSetPathArchive());
        Assert.assertNotNull(parser.getRuleSetParametersMapping());
    }

    @Test
    public void doubleProcessTest(){
        processor.process();
        processor.process();

        RuleSetParser parser = new RuleSetParser(this.options.getRuleSetPathArchive());
        Assert.assertNotNull(parser.getRuleSetParametersMapping());
    }

    @Test
    public void emptyOption(){
        this.processor.setOptions(new GeneratorOptions());
        this.processor.process();
    }
}

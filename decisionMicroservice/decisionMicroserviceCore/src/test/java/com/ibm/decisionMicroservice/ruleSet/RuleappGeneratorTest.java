package com.ibm.decisionMicroservice.ruleSet;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.codeGen.processor.RulesetExtractionProcessor;
import com.ibm.decisionMicroservice.exception.NoRuleAppGeneratedException;
import com.ibm.decisionMicroservice.utils.FileUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RuleappGeneratorTest {

    @After
    public void removeFolder(){
        FileUtil.deleteFolder("test");
    }


    @Test
    public void generationTest() throws Exception{
        String rulesetPath = this.getClass().getResource("/ruleset.dsar").getFile();
        String ruleapp = "test/test/ruleapp.jar";

        RuleappGenerator generator = new RuleappGenerator(rulesetPath,ruleapp);

        String ruleappPath = generator.generate();

        GeneratorOptions options = new GeneratorOptions();
        options.setRuleAppJarPath(ruleappPath);
        options.setRuleSetPath(generator.getRulesetPath());
        options.setOutputDir("test");

        RulesetExtractionProcessor processor = new RulesetExtractionProcessor();
        processor.setOptions(options);
        processor.process();

        assertTrue(FileUtil.compareFile(options.getRuleSetPathArchive(),rulesetPath));
    }

    @Test(expected = NoRuleAppGeneratedException.class)
    public void wrongRulesetTest(){
        String rulesetPath = this.getClass().getResource("/miniloan-xom.jar").getFile();
        String ruleapp = "test/test/ruleapp.jar";

        RuleappGenerator generator = new RuleappGenerator(rulesetPath,ruleapp);
        String ruleappPath = generator.generate();
    }

    @Test(expected = NoRuleAppGeneratedException.class)
    public void missingRulesetTest(){
        String rulesetPath = "noSuchFile.jar";
        String ruleapp = "test/test/ruleapp.jar";

        RuleappGenerator generator = new RuleappGenerator(rulesetPath,ruleapp);
        String ruleappPath = generator.generate();
    }
}

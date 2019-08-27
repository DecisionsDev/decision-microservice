package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.utils.FileUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RuleappBuilderProcessorTest {
    private GeneratorOptions options;
    private Processor processor;

    @Before
    public void init(){
        this.processor = new RuleappBuilderProcessor();
        this.options = new GeneratorOptions();
        this.processor.setOptions(this.options);
        this.options.setOutputDir("test");
    }

    @After
    public void removeFolder(){
        FileUtil.deleteFolder("test");
    }


    @Test
    public void ruleappBuildTest(){
        String rulesetPath = this.getClass().getResource("/ruleset.dsar").getFile();
        this.options.setRuleSetPathArchive(rulesetPath);

        this.processor.process();

        assertTrue(FileUtil.checkFile(this.options.getRuleAppJarPath()));
    }

    @Test
    public void noRulesetTest() {
        this.options.setRuleSetPathArchive(null);

        this.processor.process();

        assertNull(this.options.getRuleAppJarPath());
    }

    @Test
    public void ruleappPresentTest(){
        String ruleappPath = "test";
        this.options.setRuleAppJarPath(ruleappPath);

        this.processor.process();

        assertEquals(ruleappPath,this.options.getRuleAppJarPath());
    }

    @Test
    public void emptyOption(){
        this.processor.setOptions(new GeneratorOptions());
        this.processor.process();
    }


}

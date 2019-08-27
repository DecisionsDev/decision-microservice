package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.exception.MissingOptionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.ibm.decisionMicroservice.utils.FileUtil;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LibProcessorTest {
    private Processor processor;
    private GeneratorOptions options;

    @Before
    public void init(){
        this.processor = new LibProcessor();
        this.options = new GeneratorOptions();
        processor.setOptions(options);
    }

    @After
    public void removeFolder(){
        FileUtil.deleteFolder(new GeneratorOptions().getOutputDir());
    }

    @Test
    public void processTest() throws IOException {
        String xomPath = this.getClass().getResource("/miniloan-xom.jar").getPath();
        String rulePath = this.getClass().getResource("/ruleApp_test_deployment_1.0.jar").getPath();

        options.setXomJarPath(xomPath);
        options.setRuleAppJarPath(rulePath);

        processor.process();

        assertTrue(FileUtil.compareFile(xomPath,options.getXomJarPath()));

        assertTrue(FileUtil.compareFile(rulePath,options.getRuleAppJarPath()));
    }

    @Test(expected = RuntimeException.class)
    public void wrongPath(){
        options.setXomJarPath("rrrr");
        options.setRuleAppJarPath("eaaze");
        processor.process();
    }

    @Test
    public void emptyOption(){
        this.processor.setOptions(new GeneratorOptions());
        this.processor.process();
    }


}

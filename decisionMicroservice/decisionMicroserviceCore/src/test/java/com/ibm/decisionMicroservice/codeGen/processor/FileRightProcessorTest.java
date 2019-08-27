package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.utils.FileUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FileRightProcessorTest {
    private Processor processor;
    private GeneratorOptions options;
    private String output = "rightfile";

    @Before
    public void init() throws Exception{
        this.processor = new FileRightProcessor();
        this.options = new GeneratorOptions();
        options.setOutputDir(output);

        this.processor.setOptions(options);

        FileUtil.createFile(output + File.separator +"build.sh");
    }

    @After
    public void cleanup(){
        FileUtil.deleteFolder(output);
    }

    @Test
    public void giveRightTest(){
        File file = new File(this.options.getOutputDir() + File.separator +"build.sh");

        assertFalse(file.canExecute());
        this.processor.process();
        assertTrue(file.canExecute());
    }

    @Test
    public void missingFileTest(){
        this.options.setOutputDir("test");

        File file = new File(this.options.getOutputDir() + File.separator +"build.sh");

        assertFalse(file.exists());
        this.processor.process();
    }

    @Test
    public void emptyOption(){
        this.processor.setOptions(new GeneratorOptions());
        this.processor.process();
    }
}

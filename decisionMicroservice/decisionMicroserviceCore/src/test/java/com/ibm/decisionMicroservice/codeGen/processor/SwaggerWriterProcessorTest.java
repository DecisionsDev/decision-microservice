package com.ibm.decisionMicroservice.codeGen.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.utils.FileUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SwaggerWriterProcessorTest {
    private GeneratorOptions options;
    private Processor processor;

    @Before
    public void init() throws Exception{
        this.options = new GeneratorOptions();
        this.processor = new SwaggerWriterProcessor();
        this.options.setOutputDir("test");

        this.processor.setOptions(options);

        String swaggerPath = this.getClass().getResource("/MiniloanruleappMiniloanrulesDecisionService.json").getPath();
        this.options.setSwaggerPath(swaggerPath);
        Processor swaggerLoader = new SwaggerLoaderProcessor();
        swaggerLoader.setOptions(this.options);
        swaggerLoader.process();
    }

    @After
    public void cleanup(){
        FileUtil.deleteFolder(this.options.getOutputDir());
    }



    @Test
    public void writeTest() throws Exception{
        this.processor.process();
        String path = this.options.getSwaggerPath();

        assertTrue(FileUtil.checkFile(path));
    }
}

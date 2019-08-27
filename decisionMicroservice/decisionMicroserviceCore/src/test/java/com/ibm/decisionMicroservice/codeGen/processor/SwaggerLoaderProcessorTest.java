package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.exception.IllFormatedSwaggerException;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.ibm.decisionMicroservice.utils.FileUtil;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;


public class SwaggerLoaderProcessorTest {
    private Processor swaggerLoader;
    private GeneratorOptions options;

    @Before
    public void init(){
        this.swaggerLoader = new SwaggerLoaderProcessor();
        options = new GeneratorOptions();
        swaggerLoader.setOptions(options);
    }

    @After
    public void cleanup(){
        FileUtil.deleteFolder("./test");
    }

    @Test
    public void loadTest() throws Exception{
        String swaggerPath = this.getClass().getResource("/MiniloanruleappMiniloanrulesDecisionService.json").getPath();
        options.setSwaggerPath(swaggerPath);

        Swagger expected = new SwaggerParser().parse(new String(Files.readAllBytes(Paths.get(swaggerPath))));
        swaggerLoader.process();

        assertEquals(expected,this.options.getSwagger());
    }

    @Test(expected = RuntimeException.class)
    public void wrongFile(){
        options.setSwaggerPath("noFile");

        swaggerLoader.process();
    }

    @Test (expected = IllFormatedSwaggerException.class)
    public void wrongFormattest() throws Exception{
        String testFilePath = "./test/test.json";
        FileUtil.createFile(testFilePath);

        options.setSwaggerPath(testFilePath);
        swaggerLoader.process();
    }

    @Test
    public void emptyOption(){
        this.swaggerLoader.setOptions(new GeneratorOptions());
        this.swaggerLoader.process();
    }
}

package com.ibm.decisionMicroservice.codeGen.templater;

import com.ibm.decisionMicroservice.codeGen.AdditionalProperties;
import com.ibm.decisionMicroservice.codeGen.template.TemplateEngine;
import com.ibm.decisionMicroservice.codeGen.template.TemplateName;
import com.ibm.decisionMicroservice.utils.FileUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class TemplateEngineTest {

    private TemplateEngine engine;

    @Before
    public void init(){
        engine = new TemplateEngine();
    }

    @After
    public void cleanup(){
        FileUtil.deleteFolder("./test");
    }

    @Test
    public void noParamTemplateTest() throws Exception{
        String dest = "./test/test/Application.java";

        Map<String,Object> params = new HashMap<>();
        params.put("package","default");

        engine.process(TemplateName.MICRONAUT_APP,
                dest,
                params);

        assertTrue(FileUtil.checkFile(dest));
    }

    @Test
    public void requestParamTemplateTest() throws Exception{
        String dest = "./test/test/Response.java";

        Map<String,Object> params = new HashMap<>();

        Map<String,Object> input1 = new HashMap<>();
        input1.put("name","loan");
        input1.put("type","miniloan.loan");

        Map<String,Object> input2 = new HashMap<>();
        input2.put("name","borrower");
        input2.put("type","miniloan.borrower");


        params.put("inputs", Arrays.asList(input1,input2));
        params.put("package","default");

        params.put(AdditionalProperties.DEFAULT_ID,"");
        params.put(AdditionalProperties.DECISION_ID,"");

        engine.process(TemplateName.MICRONAUT_REQUEST,
                dest,params);

        assertTrue(FileUtil.checkFile(dest));

    }

}

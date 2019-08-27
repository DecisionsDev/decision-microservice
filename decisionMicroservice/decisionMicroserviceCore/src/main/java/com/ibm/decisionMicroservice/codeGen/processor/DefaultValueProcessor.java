package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.AdditionalProperties;
import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//fill the unset parameters with default values
public class DefaultValueProcessor implements Processor {
    public final String DEFAULT_OUTPUT_DIR = "output";
    public static String DEFAULT_DOCKER_NAME = "default";
    public static String DEFAULT_BUILDING_IMAGE = "maven:3.6.1-ibmjava";
    public static String DEFAULT_PORT = "8080";
    private static String DEFAULT_SERVICE_PATH = "/execution";
    private static String DEFAULT_ID_NAME = "__DecisionID__";
    private static String DEFAULT_ID = "default";
    private static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
    private static String DEFAULT_POOL_SIZE = "100";


    private GeneratorOptions options;

    private Map<String,String> properties;

    public DefaultValueProcessor(){
        this.properties = new HashMap<>();

        properties.put(AdditionalProperties.CONTAINER_NAME,DEFAULT_DOCKER_NAME);
        properties.put(AdditionalProperties.BUILDING_IMAGE,DEFAULT_BUILDING_IMAGE);
        properties.put(AdditionalProperties.SERVICE_PATH,DEFAULT_SERVICE_PATH);
        properties.put(AdditionalProperties.DECISION_ID,DEFAULT_ID_NAME);
        properties.put(AdditionalProperties.DEFAULT_ID,DEFAULT_ID);
        properties.put(AdditionalProperties.POOL_SIZE,DEFAULT_POOL_SIZE);




        properties.put(AdditionalProperties.DATE_FORMAT,DEFAULT_DATE_FORMAT);
        properties.put("title","springServiceGeneration");
        properties.put("groupId","decisionMicroservice.generation");
        properties.put("serverPort",DEFAULT_PORT);
    }


    @Override
    public void setOptions(GeneratorOptions options) {
        this.options = options;

    }

    @Override
    public void process() {
        if(this.options.getOutputDir() == null){
            this.options.setOutputDir(DEFAULT_OUTPUT_DIR);
        }

        this.properties.putAll(this.options.getAdditionalProperties());
        this.options.setAdditionalProperties(this.properties);


    }


}

package com.ibm.decisionMicroservice.codeGen;

import com.ibm.decisionMicroservice.codeGen.parameters.Parameter;
import io.swagger.models.Swagger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneratorOptions {
    private final String DEFAULT_OUTPUT_DIR = "output";
    private static String DEFAULT_DOCKER_NAME = "default";
    private static String DEFAULT_BUILDING_IMAGE = "maven:3.6.1-ibmjava";

    private String outputDir = DEFAULT_OUTPUT_DIR;
    private String ruleAppProjectPath;
    private String ruleAppDeploymentName;
    private String xomJarPath;
    private String ruleAppJarPath;
    private String ruleSetPath;
    private String ruleSetPathArchive;
    private Map<String,String> xomClassMapping;
    private Map<String,String> additionalProperties;
    private List<Parameter> inputs;
    private List<Parameter> outputs;

    private String swaggerPath;
    private Swagger swagger;

    private String framework;



    public GeneratorOptions(){
        this.additionalProperties = new HashMap<>();
        this.xomClassMapping = new HashMap<>();
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
    }

    public String getRuleSetPathArchive() {
        return ruleSetPathArchive;
    }

    public void setRuleSetPathArchive(String ruleSetPathArchive) {
        this.ruleSetPathArchive = ruleSetPathArchive;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public String getSwaggerPath() {
        return swaggerPath;
    }

    public void setSwaggerPath(String swaggerPath) {
        this.swaggerPath = swaggerPath;
    }

    public String getXomJarPath() {
        return xomJarPath;
    }

    public void setXomJarPath(String xomJarPath) {
        this.xomJarPath = xomJarPath;
    }

    public String getRuleAppJarPath() {
        return ruleAppJarPath;
    }

    public void setRuleAppJarPath(String ruleAppJarPath) {
        this.ruleAppJarPath = ruleAppJarPath;
    }

    public String getRuleSetPath() {
        return ruleSetPath;
    }

    public void setRuleSetPath(String ruleSetPath) {
        this.ruleSetPath = ruleSetPath;
    }

    public Map<String, String> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, String> additionalProperties) {
        this.additionalProperties.putAll(additionalProperties);
    }

    public Map<String, String> getXomClassMapping() {
        return xomClassMapping;
    }

    public void setXomClassMapping(Map<String, String> xomClassMapping) {
        this.xomClassMapping = xomClassMapping;
    }

    public String getRuleAppProjectPath() {
        return ruleAppProjectPath;
    }

    public void setRuleAppProjectPath(String ruleAppProjectPath) {
        this.ruleAppProjectPath = ruleAppProjectPath;
    }

    public String getRuleAppDeploymentName() {
        return ruleAppDeploymentName;
    }

    public void setRuleAppDeploymentName(String ruleAppDeploymentName) {
        this.ruleAppDeploymentName = ruleAppDeploymentName;
    }

    public Swagger getSwagger() {
        return swagger;
    }

    public void setSwagger(Swagger swagger) {
        this.swagger = swagger;
    }

    public List<Parameter> getInputs() {
        return inputs;
    }

    public void setInputs(List<Parameter> inputs) {
        this.inputs = inputs;
    }

    public List<Parameter> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<Parameter> outputs) {
        this.outputs = outputs;
    }

    public String getFramework() {
        return framework;
    }

    public void setFramework(String framework) {
        this.framework = framework;
    }
}

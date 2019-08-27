package com.ibm.decisionMicroservice.codeGen.language.micronaut;

import com.ibm.decisionMicroservice.codeGen.Generator;
import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.codeGen.template.TemplateEngine;
import com.ibm.decisionMicroservice.codeGen.template.TemplateName;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MicronautGenerator implements Generator {
    private GeneratorOptions options;
    private TemplateEngine engine;

    private final String CODE_DIRECTORY = "src/main/java/decision/service";
    private final String RESOURCES_DIRECTORY = "src/main/resources";

    public MicronautGenerator(){
        engine = new TemplateEngine();
    }

    @Override
    public void setOptions(GeneratorOptions options) {
        this.options = options;
    }

    @Override
    public void generate() {
        Map<String,Object> params = new HashMap<>();
        params.put("package","decision.service");
        params.putAll(this.options.getAdditionalProperties());

        params.put("inputs",this.options.getInputs());
        params.put("outputs",this.options.getOutputs());
        params.put("rulesetPath",options.getRuleSetPath());
        params.put("ruleAppPath",options.getRuleAppJarPath());
        params.put("xomPath",options.getXomJarPath());

        buildFiles(params);

    }

    private void buildFiles(Map<String, Object> params) {
        engine.process(TemplateName.MICRONAUT_REQUEST,
                this.options.getOutputDir() + File.separator + CODE_DIRECTORY + File.separator + "Request.java",
                params);

        engine.process(TemplateName.MICRONAUT_RESPONSE,
                this.options.getOutputDir() + File.separator + CODE_DIRECTORY + File.separator + "Response.java",
                params);

        engine.process(TemplateName.MICRONAUT_APP,
                this.options.getOutputDir() + File.separator + CODE_DIRECTORY + File.separator + "Application.java",
                params);

        engine.process(TemplateName.MICRONAUT_CONTROLLER,
                this.options.getOutputDir() + File.separator + CODE_DIRECTORY + File.separator + "Controller.java",
                params);

        engine.process(TemplateName.MICRONAUT_REQUEST,
                this.options.getOutputDir() + File.separator + CODE_DIRECTORY + File.separator + "Request.java",
                params);

        engine.process(TemplateName.MICRONAUT_POM,
                this.options.getOutputDir() + File.separator +  "pom.xml",
                params);

        engine.process(TemplateName.MICRONAUT_RES_APPLICATION,
                this.options.getOutputDir() + File.separator + RESOURCES_DIRECTORY + File.separator + "application.yml",
                params);

        engine.process(TemplateName.DOCKERFILE,
                this.options.getOutputDir() + File.separator + "Dockerfile",
                params);

        engine.process(TemplateName.SCRIPT_DOCKERBUILD,
                this.options.getOutputDir() + File.separator + "buildDocker.sh",
                params);

        engine.process(TemplateName.SCRIPT_START,
                this.options.getOutputDir() + File.separator + "startDocker.sh",
                params);

        engine.process(TemplateName.SCRIPT_BUILD,
                this.options.getOutputDir() + File.separator + "build.sh",
                params);

        engine.process(TemplateName.MICRONAUT_LOGBACK,
                this.options.getOutputDir() + File.separator + RESOURCES_DIRECTORY + File.separator + "logback.xml",
                params);
    }
}

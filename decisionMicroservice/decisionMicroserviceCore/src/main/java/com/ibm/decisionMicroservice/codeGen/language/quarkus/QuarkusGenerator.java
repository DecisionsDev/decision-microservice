package com.ibm.decisionMicroservice.codeGen.language.quarkus;

import com.ibm.decisionMicroservice.codeGen.Generator;
import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.codeGen.template.TemplateEngine;
import com.ibm.decisionMicroservice.codeGen.template.QuarkusTemplateName;
import ilog.rules.res.model.IlrFormatException;
import ilog.rules.res.model.IlrPath;
import ilog.rules.res.model.IlrVersion;
import ilog.rules.res.util.IlrVersionInfo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class QuarkusGenerator implements Generator {
    private GeneratorOptions options;
    private TemplateEngine engine;
    private final String SRC_MAIN = "src/main";
    private final String CODE_DIRECTORY = SRC_MAIN + "/java/decision/service";

    private final String MODEL_DIRECTORY = "model";

    private final String RESOURCES_DIRECTORY = SRC_MAIN + "/resources";
    private final String DOCKER_SRC_DIRECTORY = SRC_MAIN + "/docker";
    private final String DEFAULT_RULESETVERSION = "1.0";
    private final String DEFAULT_RULESETNAME = "decisionservice";
    private final String DEFAULT_RULEAPPVERSION = "1.0";
    private final String DEFAULT_RULEAPPNAME = "decisionservice";
    private final String DEFAULT_RULESETPAH = DEFAULT_RULEAPPNAME + "/" + DEFAULT_RULEAPPVERSION + "/" + DEFAULT_RULESETNAME + "/" + DEFAULT_RULESETVERSION;

    public QuarkusGenerator() {
        engine = new TemplateEngine();
    }

    @Override
    public void setOptions(GeneratorOptions options) {
        this.options = options;
    }

    @Override
    public void generate() {
        Map<String, Object> params = new HashMap<>();
        params.put("package", "decision.service");
        params.putAll(this.options.getAdditionalProperties());

        params.put("inputs", this.options.getInputs());
        params.put("outputs", this.options.getOutputs());
        params.put("rulesetPath", options.getRuleSetPath());
        params.put("ruleAppName", DEFAULT_RULEAPPNAME);
        params.put("ruleAppVersion", DEFAULT_RULESETVERSION);
        params.put("rulesetName", DEFAULT_RULEAPPNAME);
        params.put("rulesetVersion", DEFAULT_RULESETVERSION);
        params.put("ODMVersion", IlrVersionInfo.IMPLEMENTATION_VERSION);
        String rulesetPath = options.getRuleSetPath();
        if (rulesetPath == null) rulesetPath = DEFAULT_RULESETPAH;

        try {
            IlrPath rsPath = IlrPath.parsePath(rulesetPath);
            params.put("rulesetName", rsPath.getRulesetName());
            String rsVersion =DEFAULT_RULESETVERSION;
            if(rsPath.getRulesetVersion()!=null)rsVersion=rsPath.getRulesetVersion().toString();
            params.put("rulesetVersion",
                    rsVersion);
            params.put("ruleAppName", rsPath.getRuleAppName());
            String raVersion = raVersion=DEFAULT_RULEAPPVERSION;
            if(rsPath.getRuleAppVersion()!=null)  raVersion=rsPath.getRuleAppVersion().toString();

            params.put("ruleAppVersion", raVersion);

            // Quarkus don't like _ and uppercase.
            params.put("rulesetArtifactName", rsPath.getRulesetName().replaceAll("[^A-Za-z0-9 ]", "").toLowerCase());
        } catch (IlrFormatException e) {
            System.err.println("Ruleset Path %s " + options.getRuleSetPath() + " cannot be parsed.");
        }

        params.put("ruleAppPath", options.getRuleAppJarPath());

        params.put("xomPath", options.getXomJarPath());


        buildFiles(params);

    }

    private void buildFiles(Map<String, Object> params) {

        engine.process(QuarkusTemplateName.QUARKUS_RESPONSE,
                this.options.getOutputDir() + File.separator + CODE_DIRECTORY + File.separator + MODEL_DIRECTORY +
                        File.separator + "ResponseService.java",
                params);
        engine.process(QuarkusTemplateName.QUARKUS_REQUEST,
                this.options.getOutputDir() + File.separator + CODE_DIRECTORY + File.separator + MODEL_DIRECTORY +
                        File.separator + "RequestService.java",
                params);
        engine.process(QuarkusTemplateName.QUARKUS_ERROR,
                this.options.getOutputDir() + File.separator + CODE_DIRECTORY + File.separator + MODEL_DIRECTORY +
                        File.separator + "ErrorService.java",
                params);
        engine.process(QuarkusTemplateName.QUARKUS_API,
                this.options.getOutputDir() + File.separator + CODE_DIRECTORY +
                        File.separator + "DecisionApi.java",
                params);
        engine.process(QuarkusTemplateName.QUARKUS_CONTROLLER,
                this.options.getOutputDir() + File.separator + CODE_DIRECTORY +
                        File.separator + "DecisionController.java",
                params);
        engine.process(QuarkusTemplateName.QUARKUS_POM,
                this.options.getOutputDir() + File.separator + "pom.xml",
                params);
        engine.process(QuarkusTemplateName.QUARKUS_APPLICATION,
                this.options.getOutputDir() + File.separator + RESOURCES_DIRECTORY + File.separator + "application.properties",
                params);
        engine.process(QuarkusTemplateName.QUARKUS_INDEX_HTML,
                this.options.getOutputDir() + File.separator + RESOURCES_DIRECTORY + File.separator + "META-INF" + File.separator + "resources" + File.separator + "index.html",
                params);
        engine.process(QuarkusTemplateName.QUARKUS_DOCKER_JVM,
                this.options.getOutputDir() + File.separator + DOCKER_SRC_DIRECTORY + File.separator + "Dockerfile.jvm",
                params);
        engine.process(QuarkusTemplateName.QUARKUS_DOCKER_LEGACY,
                this.options.getOutputDir() + File.separator + DOCKER_SRC_DIRECTORY + File.separator + "Dockerfile.legacy-jar",
                params);
        engine.process(QuarkusTemplateName.QUARKUS_DOCKER_NATIVE,
                this.options.getOutputDir() + File.separator + DOCKER_SRC_DIRECTORY + File.separator + "Dockerfile.native",
                params);
        engine.process(QuarkusTemplateName.QUARKUS_DOCKER_NATIVE_MICRO,
                this.options.getOutputDir() + File.separator + DOCKER_SRC_DIRECTORY + File.separator + "Dockerfile.native-micro",
                params);
        engine.process(QuarkusTemplateName.QUARKUS_README,
                this.options.getOutputDir() + File.separator + "README.md",
                params);
    }
}

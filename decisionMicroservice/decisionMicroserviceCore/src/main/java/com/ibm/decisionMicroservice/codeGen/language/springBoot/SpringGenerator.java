package com.ibm.decisionMicroservice.codeGen.language.springBoot;

import com.ibm.decisionMicroservice.codeGen.Generator;
import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import io.swagger.codegen.ClientOptInput;
import io.swagger.codegen.ClientOpts;
import io.swagger.codegen.DefaultGenerator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * generate a spring project based on the given Open API spec file and parameters
 * rely on the swagger-codegen generator
 */
public class SpringGenerator implements Generator {
    private static String XOM_PATH = "xomPath";
    private static String RULE_APP_PATH = "ruleAppPath";
    private static String RULESET_PATH = "rulesetPath";

    private SpringDockerCodegen config;
    private ClientOpts opts;
    private Map<String,String> properties;

    private GeneratorOptions options;

    public SpringGenerator(){
        this.config = new SpringDockerCodegen();
        this.opts = new ClientOpts();
        this.properties = new HashMap<>();
    }

    @Override
    public void setOptions(GeneratorOptions options) {
        this.options = options;
    }

    @Override
    public void generate() {
        DefaultGenerator generator = new DefaultGenerator();
        ClientOptInput input = new ClientOptInput();

        this.properties.putAll(this.options.getAdditionalProperties());

        this.config.addAdditionalProperties(XOM_PATH,this.options.getXomJarPath());
        this.config.addAdditionalProperties(RULE_APP_PATH,this.options.getRuleAppJarPath());
        this.config.addAdditionalProperties(RULESET_PATH,this.options.getRuleSetPath());

        this.config.setOutputDir(this.options.getOutputDir());
        this.opts.setOutputDirectory( this.options.getOutputDir());

        this.config.addImportMappings(this.options.getXomClassMapping());

        this.opts.setProperties(this.properties);
        this.options.getAdditionalProperties().entrySet().stream()
                .forEach(entry -> this.config.addAdditionalProperties(entry.getKey(),entry.getValue()));
        input.swagger(this.options.getSwagger()).opts(this.opts).config(this.config);
        generator.opts(input).generate();
    }




}

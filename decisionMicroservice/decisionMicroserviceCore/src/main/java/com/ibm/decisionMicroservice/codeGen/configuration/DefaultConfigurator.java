package com.ibm.decisionMicroservice.codeGen.configuration;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DefaultConfigurator {
    private List<Configuration> configurations;

    public DefaultConfigurator(){
        this.configurations = Arrays.asList(
                new RulesetConfiguration(),
                new RuleappConfiguration(),
                new RuleprojectConfiguration()
        );
    }

    public Optional<Configuration> getConfig(GeneratorOptions options){
        return configurations.stream()
                .filter(config -> config.isCorrect(options))
                .findFirst();
    }
}

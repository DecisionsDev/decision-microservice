package com.ibm.decisionMicroservice.codeGen.configuration;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.codeGen.processor.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RulesetConfiguration implements Configuration{

    @Override
    public boolean isCorrect(GeneratorOptions options) {
        return options.getXomJarPath() != null && options.getRuleSetPathArchive() != null;
    }

    @Override
    public List<Processor> getProcessors() {
        return new ArrayList<>(Arrays.asList(
                new RuleappBuilderProcessor()
        ));
    }

    @Override
    public String name() {
        return "Rule set configuration";
    }
}

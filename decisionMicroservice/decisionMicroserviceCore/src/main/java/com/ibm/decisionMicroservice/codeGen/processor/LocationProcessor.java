package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.codeGen.processor.Processor;

import java.nio.file.Path;
import java.nio.file.Paths;

//relativize artifact paths
public class LocationProcessor implements Processor {
    private GeneratorOptions options;

    @Override
    public void setOptions(GeneratorOptions options) {
        this.options = options;
    }

    @Override
    public void process() {
        this.options.setXomJarPath(relativizeFromOutput(this.options.getXomJarPath()));
        this.options.setRuleAppJarPath(relativizeFromOutput(this.options.getRuleAppJarPath()));
    }

    private String relativizeFromOutput(String path){
        Path output = Paths.get(this.options.getOutputDir());
        Path current = Paths.get(path);

        return output.relativize(current).toString();
    }
}

package com.ibm.decisionMicroservice.codeGen.configuration;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.codeGen.processor.Processor;

import java.util.List;

public interface Configuration {
    boolean isCorrect(GeneratorOptions options);
    List<Processor> getProcessors();
    String name();
}

package com.ibm.decisionMicroservice.codeGen;

public interface Generator {
    void setOptions(GeneratorOptions options);
    void generate();
}
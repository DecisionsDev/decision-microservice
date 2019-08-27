package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;

//implemented by every processor
// processor are meant to be executed before of after the code generation
public interface Processor {

    void setOptions(GeneratorOptions options);
    public abstract void process();
}

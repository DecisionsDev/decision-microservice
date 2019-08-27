package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.codeGen.parameters.Directions;
import com.ibm.decisionMicroservice.codeGen.parameters.Parameter;

//add Decision as both an input and ouput parameter
public class DecisionIdProcessor implements Processor {
    private String decisionIdName = "__DecisionId__";

    private GeneratorOptions options;

    @Override
    public void setOptions(GeneratorOptions options) {
        this.options = options;
    }

    @Override
    public void process() {
        Parameter decisionId = new Parameter(decisionIdName,"String", Directions.INOUT);
        options.getInputs().add(decisionId);
        options.getOutputs().add(decisionId);
    }
}

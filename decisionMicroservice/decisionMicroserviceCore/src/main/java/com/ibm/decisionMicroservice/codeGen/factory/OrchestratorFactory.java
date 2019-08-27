package com.ibm.decisionMicroservice.codeGen.factory;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.codeGen.GeneratorOrchestrator;

public class OrchestratorFactory implements GeneratorOrchestratorFactory {

    public GeneratorOrchestrator makeOrchestrator(GeneratorOptions options){
        Frameworks frameworks = Frameworks.getLanguage(options.getFramework());

        return frameworks.getFactory().makeOrchestrator(options);
    }
}

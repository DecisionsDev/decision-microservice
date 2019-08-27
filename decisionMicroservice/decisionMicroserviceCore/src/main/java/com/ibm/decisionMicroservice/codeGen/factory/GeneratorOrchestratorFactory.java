package com.ibm.decisionMicroservice.codeGen.factory;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.codeGen.GeneratorOrchestrator;

public interface GeneratorOrchestratorFactory {
    GeneratorOrchestrator makeOrchestrator(GeneratorOptions options);
}

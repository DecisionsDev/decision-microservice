package com.ibm.decisionMicroservice.codeGen.factory;

import com.ibm.decisionMicroservice.exception.NoSuchFrameworkException;

public enum Frameworks {
    MICRONAUT("micronaut",new MicronautOrchestratorFactory()),
    SPRINGBOOT("spring", new SpringBootOrchestratorFactory());

    private String name;
    private GeneratorOrchestratorFactory factory;

    Frameworks(String name, GeneratorOrchestratorFactory factory){
        this.name = name;
        this.factory = factory;
    }

    public String getName() {
        return name;
    }

    public GeneratorOrchestratorFactory getFactory() {
        return factory;
    }

    public static Frameworks getLanguage(String language){
        for(Frameworks lang : Frameworks.values()){
            if(lang.getName().equals(language)){
                return lang;
            }
        }
        throw new NoSuchFrameworkException(language);
    }
}

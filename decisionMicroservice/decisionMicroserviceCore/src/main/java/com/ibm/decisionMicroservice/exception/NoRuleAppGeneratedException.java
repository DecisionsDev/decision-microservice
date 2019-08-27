package com.ibm.decisionMicroservice.exception;

public class NoRuleAppGeneratedException extends RuntimeException {
    public NoRuleAppGeneratedException(){
        super("No rule App were generated with the given configuration");
    }

    public NoRuleAppGeneratedException(String messages,Throwable throwable){
        super(messages,throwable);
    }

    public NoRuleAppGeneratedException(String messages){
        super(messages);
    }

    public NoRuleAppGeneratedException(Throwable throwable){
        super(throwable);
    }

}

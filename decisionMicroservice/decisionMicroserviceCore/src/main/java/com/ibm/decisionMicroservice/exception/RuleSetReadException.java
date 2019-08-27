package com.ibm.decisionMicroservice.exception;

public class RuleSetReadException extends RuntimeException {

    public RuleSetReadException(Throwable throwable){
        super("cannot read the given ruleset archive",throwable);
    }
}

package com.ibm.decisionMicroservice.exception;

public class MissingConfigException extends RuntimeException {
    public MissingConfigException(){
        super("no configuration found for the given options");
    }
}

package com.ibm.decisionMicroservice.exception;

public class MissingOptionException extends RuntimeException{

    public MissingOptionException(String optionName,Throwable throwable){
        super("Missing option : " + optionName);
    }

    public MissingOptionException(String optionName){
        super("Missing option : " + optionName);
    }
}

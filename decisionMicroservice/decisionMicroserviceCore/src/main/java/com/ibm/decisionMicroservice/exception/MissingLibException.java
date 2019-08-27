package com.ibm.decisionMicroservice.exception;

public class MissingLibException extends RuntimeException {
    public MissingLibException(String filename,Throwable e){
        super("missing file in library : " + filename,e);
    }
}

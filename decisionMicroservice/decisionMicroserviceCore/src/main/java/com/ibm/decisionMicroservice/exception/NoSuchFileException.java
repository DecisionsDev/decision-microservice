package com.ibm.decisionMicroservice.exception;

public class NoSuchFileException extends RuntimeException {

    public NoSuchFileException(String filename,Throwable throwable){
        super("file does not exist : " + filename,throwable);
    }
    public NoSuchFileException(String filename){
        super("file does not exist : " + filename);
    }
}

package com.ibm.decisionMicroservice.exception;

public class MissingFileException extends RuntimeException{
    private String filename;

    public MissingFileException(String filename){
        super(filename);
    }

    public MissingFileException(String filename, Throwable throwable){
        super("No such File : " + filename,throwable);
    }

    public String getFilename(){
        return this.filename;
    }
}

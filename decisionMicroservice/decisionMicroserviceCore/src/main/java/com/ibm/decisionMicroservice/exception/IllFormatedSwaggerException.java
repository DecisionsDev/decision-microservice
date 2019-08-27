package com.ibm.decisionMicroservice.exception;

public class IllFormatedSwaggerException extends RuntimeException {
    public IllFormatedSwaggerException(String message, Throwable cause) {
        super(message, cause);
    }
}

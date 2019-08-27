package com.ibm.decisionMicroservice.exception;

import com.ibm.decisionMicroservice.codeGen.factory.Frameworks;

public class NoSuchFrameworkException extends RuntimeException{

    public NoSuchFrameworkException(String language){
        super(buildMessage(language));
    }

    private static String buildMessage(String language) {
        StringBuilder builder = new StringBuilder();
        builder.append("No such framework : ");
        builder.append(language);

        builder.append("available frameworks are : ");

        for(Frameworks frameworks : Frameworks.values()){
            builder.append(frameworks.getName());
            builder.append(" ");
        }
        return builder.toString();
    }
}

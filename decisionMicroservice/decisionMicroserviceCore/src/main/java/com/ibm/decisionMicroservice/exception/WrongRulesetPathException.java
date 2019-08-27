package com.ibm.decisionMicroservice.exception;

public class WrongRulesetPathException extends RuntimeException{

    public WrongRulesetPathException(String rulesetPath,String ruleAppPath,Throwable throwable){
        super(rulesetPath + " cannot be found in given rule app : " + ruleAppPath,throwable);
    }
}

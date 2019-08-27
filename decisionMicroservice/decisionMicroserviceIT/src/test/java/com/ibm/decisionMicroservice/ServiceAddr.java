package com.ibm.decisionMicroservice;

public class ServiceAddr {
    public static final String ENVHOSTKEY = "testhost";

    static {
        if (System.getProperty(ENVHOSTKEY) !=null){
            HOST = System.getProperty(ENVHOSTKEY);
        } else {
            HOST = "localhost:9090";
        }
    }
    public static String HOST;
    public static final String MINILOAN_PATH = "http://" + HOST + "/DecisionService/rest/miniloanruleapp/miniloanrules";
    public static final String SHIPMENT_PATH = "http://" + HOST +"/DecisionService/rest/Shipment_Pricing_RuleApp/Shipment_Pricing";
    public static final String LOANSCORE_PATH = "http://" + HOST +"/DecisionService/rest/LoanValidationDS/loan_validation_with_score_and_grade";
    public static final String LOANPROD_PATH = "http://" + HOST +"/DecisionService/rest/LoanValidationDS/loan_validation_production";
    public static final String HELLO_PATH = "http://" + HOST +"/DecisionService/rest/simple_dep/1.0/simple_dop/1.0";


}

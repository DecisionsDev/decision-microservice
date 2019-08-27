package com.ibm.decisionMicroservice.ruleSet;

import com.ibm.decisionMicroservice.exception.NoRuleAppGeneratedException;
import com.ibm.rules.build.DecisionOperationInformation;
import com.ibm.rules.build.RuleAppBuilder;
import com.ibm.rules.build.RuleAppBuilderParameters;
import com.ibm.rules.build.RuleAppBuilderResults;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RuleappGenerator {
    private String rulesetPath;
    private String ruleappOutputPath;

    private String rulesetName = "ruleset";
    private String ruleappName = "ruleapp";

    public RuleappGenerator(String rulesetPath, String ruleappOutputPath) {
        this.rulesetPath = rulesetPath;
        this.ruleappOutputPath = ruleappOutputPath;
    }

    public String generate(){
        RuleAppBuilder builder = new RuleAppBuilder();

        Path ruleappOutput = Paths.get(this.ruleappOutputPath);
        Path rulesetInput = Paths.get(this.rulesetPath);

        try {
            if (!Files.exists(ruleappOutput.getParent())) {
                Files.createDirectories(ruleappOutput.getParent());
            }
        } catch (IOException e){
            throw new NoRuleAppGeneratedException(e);
        }

        RuleAppBuilderParameters parameters = new RuleAppBuilderParameters(this.ruleappName,ruleappOutput.toUri() );
        DecisionOperationInformation information = new DecisionOperationInformation(this.rulesetName,rulesetInput.toUri());

        parameters.getDecisionOperationInformations().add(information);

        RuleAppBuilderResults results = builder.build(parameters);

        if(results.getArchiveUri() == null){
            StringBuilder errorMsgbuilder = new StringBuilder();
            results.getMessages().stream().forEach(msg -> errorMsgbuilder.append(msg.getText() + " "));
            String errorMsg = errorMsgbuilder.toString();
            throw new NoRuleAppGeneratedException(errorMsg);
        }

        return results.getArchiveUri().getPath();
    }

    public String getRulesetPath(){
        return "/" +  this.ruleappName + "/1.0/" + this.rulesetName + "/1.0";
    }
}

package com.ibm.decisionMicroservice.ruleSet;

import com.ibm.decisionMicroservice.codeGen.parameters.Directions;
import com.ibm.decisionMicroservice.codeGen.parameters.Parameter;
import com.ibm.decisionMicroservice.exception.RuleSetReadException;
import com.ibm.rules.util.engine.EngineArchiveUtil;
import ilog.rules.res.model.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class RuleSetParser {
    private byte[] rulesetContent;
    private IlrEngineType engineType;

    private Collection<IlrRulesetParameter> rulesetParameters;


    public RuleSetParser(String ruleSetPath){
        try {
            this.rulesetContent = Files.readAllBytes(Paths.get(ruleSetPath));
            InputStream engineStream = new ByteArrayInputStream(this.rulesetContent);
            this.engineType = IlrEngineType.toEngineType(EngineArchiveUtil.getEngineType(engineStream));

            InputStream archiveStream = new ByteArrayInputStream(this.rulesetContent);
            IlrRESRulesetArchive archive = IlrRESRulesetArchiveFactory.createRESRulesetArchive(engineType, archiveStream);
            this.rulesetParameters = archive.getRulesetParameters();
        } catch (IOException | IlrRESRulesetArchiveCreationException e) {
            throw new RuleSetReadException(e);
        }

    }

    public Map<String,String> getRuleSetParametersMapping(){
        try {
            Map<String, String> parametersMapping = new HashMap<>();

            for (IlrRulesetParameter parameter : this.rulesetParameters){
                String[] parameterNameSplit = parameter.getType().split("\\.");
                String parameterName = parameterNameSplit[parameterNameSplit.length - 1];
                parametersMapping.put(parameterName,parameter.getType());
                parametersMapping.put(parameter.getName(),parameter.getType());
            }

            return parametersMapping;
        } catch (NullPointerException e){
            throw new RuleSetReadException(e);
        }
    }

    public List<IlrRulesetParameter> getParameters(){
        try {
            return new ArrayList<>(this.rulesetParameters);
        } catch (Exception e){
            throw new RuleSetReadException(e);
        }
    }

    public List<Parameter> getInputParameters(){
        return this.rulesetParameters.stream()
                .filter(parameter -> {
                    return parameter.getDirection() == Directions.IN.getDirectionCode()
                            || parameter.getDirection() == Directions.INOUT.getDirectionCode();
                })
                .map(parameter -> new Parameter(parameter.getName(),parameter.getType(),Directions.IN))
                .collect(Collectors.toList());
    }

    public List<Parameter> getOutputParameters(){
        return this.rulesetParameters.stream()
                .filter(parameter -> {
                    return parameter.getDirection() == Directions.OUT.getDirectionCode()
                            || parameter.getDirection() == Directions.INOUT.getDirectionCode();
                })
                .map(parameter -> new Parameter(parameter.getName(),parameter.getType(),Directions.OUT))
                .collect(Collectors.toList());
    }


}

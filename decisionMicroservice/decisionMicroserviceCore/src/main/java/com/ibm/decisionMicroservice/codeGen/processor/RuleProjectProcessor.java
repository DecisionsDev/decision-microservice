package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.exception.MissingOptionException;
import com.ibm.decisionMicroservice.exception.NoRuleAppGeneratedException;
import com.ibm.rules.build.RuleAppInformation;
import com.ibm.rules.build.RuleProjectBuilder;
import com.ibm.rules.build.RuleProjectBuilderParameters;
import com.ibm.rules.build.RuleProjectBuilderResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

//generate ruleapp from rule project
public class RuleProjectProcessor implements Processor {
    private GeneratorOptions options;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void setOptions(GeneratorOptions options) {
        this.options = options;
    }

    @Override
    public void process() {
        if(this.options.getRuleAppJarPath() != null
                || this.options.getRuleSetPathArchive() != null
                || this.options.getXomJarPath() == null
                || this.options.getRuleAppProjectPath() == null
                || this.options.getRuleAppDeploymentName() == null){

            logger.info("ruleapp not generated from ruleproject : missing ruleproject or ruleapp already present");
            return;
        }
        try {

            Path projectPath = Paths.get(this.options.getRuleAppProjectPath());
            if(!Files.exists(projectPath)){
                throw new RuntimeException(
                        new NoSuchFileException(projectPath.toString()));
            }
            RuleProjectBuilderParameters parameters = new RuleProjectBuilderParameters(projectPath.toUri());

            Path xomPath = Paths.get(this.options.getXomJarPath());
            if(!Files.exists(xomPath)){
                throw new RuntimeException(
                        new NoSuchFileException(xomPath.toString()));
            }
            parameters.getRuntimeLibraryUris().add(xomPath.toUri());

            parameters.getRuleAppInformations().add(
                    new RuleAppInformation(this.options.getRuleAppDeploymentName(), false ));

            Path output = Paths.get(this.options.getOutputDir() + File.separator + "lib");
            if(!Files.exists(output)){
                Files.createDirectories(output);
            }
            parameters.setOutputFolderUri(output.toUri());

            RuleProjectBuilder builder = new RuleProjectBuilder();
            RuleProjectBuilderResults results = builder.build(parameters);

            if(results.getRuleAppFileUris().size() == 0){
                throw new NoRuleAppGeneratedException(getErrorMessage(results));
            }

            this.options.setRuleAppJarPath(results.getRuleAppFileUris().get(0).getPath());

            Path abs = Paths.get(this.options.getRuleAppJarPath());
            String relativePath = Paths.get(System.getProperty("user.dir")).relativize(abs).toString();

            logger.info("rule app generated into : " + relativePath);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private String getErrorMessage(RuleProjectBuilderResults results) {
        StringBuilder strBuilder = new StringBuilder();
        results.getMessages().stream()
                .forEach(message -> strBuilder.append(message.getText() + "\n"));
        return strBuilder.toString();
    }
}

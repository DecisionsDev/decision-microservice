package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.ruleSet.RuleappGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static com.ibm.decisionMicroservice.codeGen.AdditionalProperties.LIB_FOLDER;
import static com.ibm.decisionMicroservice.codeGen.AdditionalProperties.RULEAPP_NAME;

// build the ruleapp from a ruleset
public class RuleappBuilderProcessor implements Processor{
    private GeneratorOptions options;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public void setOptions(GeneratorOptions options) {
        this.options = options;
    }

    @Override
    public void process() {
        if(this.options.getRuleSetPathArchive() == null || this.options.getRuleAppJarPath() != null){
            logger.info("ruleapp not generated from ruleset : missing ruleset or ruleapp already present");
            return;
        }

        RuleappGenerator generator = new RuleappGenerator(options.getRuleSetPathArchive(),
                this.options.getOutputDir() + File.separator + LIB_FOLDER + File.separator + RULEAPP_NAME);

        this.options.setRuleAppJarPath(generator.generate());
        this.options.setRuleSetPath(generator.getRulesetPath());

        logger.info("Rule app generated in " + this.options.getRuleAppJarPath());
    }
}

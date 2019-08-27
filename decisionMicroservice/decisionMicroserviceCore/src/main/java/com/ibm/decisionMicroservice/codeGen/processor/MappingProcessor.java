package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.codeGen.parameters.Parameter;
import com.ibm.decisionMicroservice.exception.MissingOptionException;
import com.ibm.decisionMicroservice.ruleSet.RuleSetParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

// get list of necessary paramter for ruleset execution
public class MappingProcessor implements Processor{
    private GeneratorOptions options;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public void setOptions(GeneratorOptions options) {
        this.options = options;
    }

    @Override
    public void process() {
        if(this.options.getRuleSetPathArchive() == null ){
            logger.info("mapping not generated missing ruleset archive");
            return;
        }

        RuleSetParser parser = new RuleSetParser(this.options.getRuleSetPathArchive());
        this.options.setXomClassMapping(parser.getRuleSetParametersMapping());

        this.options.setInputs(parser.getInputParameters());
        this.options.setOutputs(parser.getOutputParameters());

        logger.info("parameter mapping generated");
    }
}

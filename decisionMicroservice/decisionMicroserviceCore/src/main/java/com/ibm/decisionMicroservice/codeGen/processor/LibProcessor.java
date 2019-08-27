package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.exception.MissingOptionException;
import com.ibm.decisionMicroservice.utils.FileCopier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.ibm.decisionMicroservice.codeGen.AdditionalProperties.LIB_FOLDER;
import static com.ibm.decisionMicroservice.codeGen.AdditionalProperties.RULEAPP_NAME;

//copy the necessary libraries to the output folder
public class LibProcessor implements Processor {
    private static String XOM_NAME = "xom.jar";

    private GeneratorOptions options;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void setOptions(GeneratorOptions options) {
        this.options = options;
    }

    @Override
    public void process() {
        if(this.options.getXomJarPath() == null || this.options.getRuleAppJarPath() == null){
            return;
        }
        File output = new File(this.options.getOutputDir());
        String libDir = output.getPath() + File.separator + LIB_FOLDER + File.separator;

        FileCopier copier = new FileCopier();
        copier.copy(this.options.getRuleAppJarPath(),libDir + RULEAPP_NAME);
        copier.copy(this.options.getXomJarPath(),libDir + XOM_NAME);

        this.options.setRuleAppJarPath(libDir + RULEAPP_NAME );
        this.options.setXomJarPath(libDir + XOM_NAME);
        logger.info("rule app copied to : " + this.options.getRuleAppJarPath() );
        logger.info("xom copied to : " + this.options.getXomJarPath());
    }
}

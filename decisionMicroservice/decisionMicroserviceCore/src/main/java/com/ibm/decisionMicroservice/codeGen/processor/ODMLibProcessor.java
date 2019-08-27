package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.AdditionalProperties;
import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.exception.MissingFileException;
import com.ibm.decisionMicroservice.exception.MissingLibException;
import com.ibm.decisionMicroservice.utils.FileCopier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

//copy odm libraries into target directory
public class ODMLibProcessor implements Processor {
    private GeneratorOptions options;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public void setOptions(GeneratorOptions options) {
        this.options = options;
    }

    @Override
    public void process() {
        String odmlib = System.getenv(AdditionalProperties.ENV_ODMLIB);

        if( odmlib == null){
            logger.info("odm archive not copied into target directory : missing env variable " + AdditionalProperties.ENV_ODMLIB);
            return;
        }

        String destination = this.options.getOutputDir() + File.separator + AdditionalProperties.LIB_FOLDER;
        try {
            FileCopier copier = new FileCopier();
            copier.copy(odmlib + File.separator + AdditionalProperties.J2EE_CONNECTOR, destination + File.separator + AdditionalProperties.J2EE_CONNECTOR);
            copier.copy(odmlib + File.separator + AdditionalProperties.JRULES_ENGINE, destination + File.separator + AdditionalProperties.JRULES_ENGINE);
            copier.copy(odmlib + File.separator + AdditionalProperties.JRULES_RES_EXECUTION, destination + File.separator + AdditionalProperties.JRULES_RES_EXECUTION);
            copier.copy(odmlib + File.separator + AdditionalProperties.JRULES_RES_MANAGE_TOOLS, destination + File.separator + AdditionalProperties.JRULES_RES_MANAGE_TOOLS);

            logger.info("lib copied into : " + destination);
        } catch (MissingFileException e){
            throw new MissingLibException(e.getFilename(),e);
        }
    }
}

package com.ibm.decisionMicroservice.codeGen;

import com.ibm.decisionMicroservice.codeGen.processor.AggregatorProcessor;
import com.ibm.decisionMicroservice.codeGen.processor.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneratorOrchestrator {
    private Processor preProcessor;
    private Processor postProcessor;
    private Generator generator;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public GeneratorOrchestrator(){
        this.postProcessor = new AggregatorProcessor();
        this.preProcessor = new AggregatorProcessor();
    }

    public void setPreProcessor(Processor preProcessor) {
        this.preProcessor = preProcessor;
    }

    public void setPostProcessor(Processor postProcessor) {
        this.postProcessor = postProcessor;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

    public void setOptions(GeneratorOptions options){
        this.preProcessor.setOptions(options);
        this.generator.setOptions(options);
        this.postProcessor.setOptions(options);
    }

    public void run(){
        logger.info("Starting preprocessing");
        preProcessor.process();

        logger.info("Starting file generation");
        generator.generate();

        logger.info("Starting postprocessing");
        postProcessor.process();

        logger.info("Generation Done");
    }
}

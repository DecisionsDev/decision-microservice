package com.ibm.decisionMicroservice.codeGen.factory;

import com.ibm.decisionMicroservice.codeGen.configuration.Configuration;
import com.ibm.decisionMicroservice.codeGen.configuration.DefaultConfigurator;
import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.codeGen.GeneratorOrchestrator;
import com.ibm.decisionMicroservice.codeGen.language.springBoot.SpringGenerator;
import com.ibm.decisionMicroservice.codeGen.processor.*;
import com.ibm.decisionMicroservice.exception.MissingConfigException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SpringBootOrchestratorFactory implements GeneratorOrchestratorFactory {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public GeneratorOrchestrator makeOrchestrator(GeneratorOptions options) {
        GeneratorOrchestrator orchestrator = new GeneratorOrchestrator();

        DefaultConfigurator configurator = new DefaultConfigurator();
        Optional<Configuration> configuration = configurator.getConfig(options);
        if(!configuration.isPresent()){
            throw new MissingConfigException();
        }

        logger.info("Using the configuration : " + configuration.get().name());

        List<Processor> preprocessors = new ArrayList<>();

        preprocessors.add(new DefaultValueProcessor());
        preprocessors.addAll(configuration.get().getProcessors());

        preprocessors.addAll(Arrays.asList(
                new LibProcessor(),
                new MappingProcessor(),
                new SwaggerLoaderProcessor(),
                new SwaggerGeneratorProcessor(),
                new SwaggerWriterProcessor(),
                new SwaggerNormalizerProcessor(),
                new ODMLibProcessor(),
                new LocationProcessor()
        ));

        orchestrator.setPreProcessor(new AggregatorProcessor(preprocessors));

        orchestrator.setPostProcessor(new AggregatorProcessor(
                new FileRightProcessor()
        ));

        orchestrator.setGenerator(new SpringGenerator());

        orchestrator.setOptions(options);

        return orchestrator;
    }
}

package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.exception.IllFormatedSwaggerException;
import io.swagger.parser.SwaggerParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

//load swagger in memory
public class SwaggerLoaderProcessor implements Processor {
    private GeneratorOptions options;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void setOptions(GeneratorOptions options) {
        this.options = options;
    }

    @Override
    public void process() {
        if(this.options.getSwaggerPath() == null){
            logger.info("Open api spec not loaded : no path found");
            return;
        }
        try {
            String swaggerContent = new String(Files.readAllBytes(Paths.get(this.options.getSwaggerPath())));
            SwaggerParser parser = new SwaggerParser();

            this.options.setSwagger(parser.parse(swaggerContent));

            logger.info("swagger file loaded");
        } catch (IOException e){
            throw new RuntimeException(
                    new NoSuchFileException(this.options.getSwaggerPath())
            );
        } catch (Exception e){
            throw new IllFormatedSwaggerException(this.options.getSwaggerPath(),e);
        }
    }
}

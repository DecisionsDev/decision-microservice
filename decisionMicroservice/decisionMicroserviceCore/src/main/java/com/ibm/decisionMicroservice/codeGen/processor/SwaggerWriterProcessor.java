package com.ibm.decisionMicroservice.codeGen.processor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.decisionMicroservice.codeGen.AdditionalProperties;
import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import io.swagger.models.Swagger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//write swagger to target directory
public class SwaggerWriterProcessor implements Processor {
    private GeneratorOptions options;
    private String outputFilename = "OpenApi.json" ;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public void setOptions(GeneratorOptions options) {
        this.options = options;
    }

    @Override
    public void process() {
        try {
            Swagger swagger = this.options.getSwagger();
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            String destination = this.options.getOutputDir() + File.separator + AdditionalProperties.LIB_FOLDER + File.separator + outputFilename;

            Path destinationPath = Paths.get(destination);

            if(!Files.exists(destinationPath.getParent())){
                Files.createDirectories(destinationPath.getParent());
            }

            FileOutputStream outputStream = new FileOutputStream(destination);
            mapper.writerWithDefaultPrettyPrinter().writeValue(outputStream,swagger);

            this.options.setSwaggerPath(destination);
            logger.info("Open api specification written to : " + destination);
        } catch (Exception e){
            throw new RuntimeException(e);
        }


    }
}

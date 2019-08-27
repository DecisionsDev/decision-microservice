package com.ibm.decisionMicroservice.codeGen.template;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class TemplateEngine {

    public void process(String template, String destination, Map<String,Object> parameters){
        try {
            Reader reader = new InputStreamReader(this.getClass().getResourceAsStream(template));
            Path destinationPath = Paths.get(destination);

            if(!Files.exists(destinationPath.getParent())){
                Files.createDirectories(destinationPath.getParent());
            }

            FileWriter writer = new FileWriter(destination);
            Template templateExec = Mustache.compiler().compile(reader);

            templateExec.execute(parameters,writer);
            writer.close();
        } catch (IOException e){
           throw new RuntimeException(e);
        }
    }


}

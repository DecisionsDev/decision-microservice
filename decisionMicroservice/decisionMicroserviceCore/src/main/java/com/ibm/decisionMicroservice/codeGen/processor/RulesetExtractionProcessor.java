package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.exception.MissingOptionException;
import com.ibm.decisionMicroservice.exception.NoSuchFileException;
import com.ibm.decisionMicroservice.exception.WrongRulesetPathException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

//extract ruleset from ruleapp
public class RulesetExtractionProcessor implements Processor {
    public static final String RULESET_NAME = "lib/ruleset.jar";

    private GeneratorOptions options;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void setOptions(GeneratorOptions options) {
        this.options = options;
    }

    @Override
    public void process() {
        if (options.getRuleSetPathArchive() != null
                || this.options.getRuleSetPath() == null
                || this.options.getRuleAppJarPath() == null) {
            logger.info("ruleset not extracted from ruleapp : missing ruleapp parameters or ruleset already present");
            return;
        }
        try {
            String rulesetPath = this.options.getRuleSetPath();
            if(rulesetPath.charAt(0) == '/'){
                rulesetPath = this.options.getRuleSetPath().substring(1,rulesetPath.length());
            }

            final String ruleset = rulesetPath;
            JarFile file = new JarFile(this.options.getRuleAppJarPath());
            JarEntry entry = file.stream()
                    .filter(jarEntry -> jarEntry.getName().contains(ruleset))
                    .findFirst()
                    .get();

            String rulesetOutputPath = createOutputFolder();
            extractRuleSet(file, entry, rulesetOutputPath);

            this.options.setRuleSetPathArchive(rulesetOutputPath);
            logger.info("ruleset extracted into : " + this.options.getRuleSetPathArchive());
        } catch (IOException e) {
            throw new NoSuchFileException(this.options.getRuleAppJarPath());
        } catch (NoSuchElementException e){
            throw new WrongRulesetPathException(this.options.getRuleSetPath(),this.options.getRuleAppJarPath(),e);
        }
    }

    private void extractRuleSet(JarFile file, JarEntry entry, String rulesetOutputPath) throws IOException {
        InputStream stream = file.getInputStream(entry);
        int data;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        while((data = stream.read()) != -1){
            byteStream.write(data);
        }

        Files.write(Paths.get(rulesetOutputPath),byteStream.toByteArray());
    }

    private String createOutputFolder() throws IOException {
        String rulesetOutputPath = this.options.getOutputDir() + File.separator + RULESET_NAME;

        Path jarDestination = Paths.get(rulesetOutputPath);
        if (!Files.exists(jarDestination.getParent())) {
            Files.createDirectories(jarDestination.getParent());
        }
        return rulesetOutputPath;
    }
}

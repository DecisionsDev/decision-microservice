package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Paths;

//set generated script as executables
public class FileRightProcessor implements Processor {
    private GeneratorOptions options;
    private String buildScriptName = "build.sh";
    private String buildDockerScriptName = "buildDocker.sh";
    private String startDockerScriptName = "startDocker.sh";

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public void setOptions(GeneratorOptions options) {
        this.options = options;
    }

    @Override
    public void process() {
        giveExecRight(this.buildScriptName);
        giveExecRight(this.buildDockerScriptName);
        giveExecRight(this.startDockerScriptName);
    }

    private void giveExecRight(String fileName) {
        File file = new File(options.getOutputDir() + File.separator + fileName);
        file.setExecutable(true);


        if (file.exists()){
            logger.info(file.getName() + " is executable");
        }
    }
}

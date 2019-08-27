package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.exception.MissingOptionException;
import com.ibm.decisionMicroservice.exception.NoRuleAppGeneratedException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.ibm.decisionMicroservice.utils.FileUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RuleProjectProcessorTest {
    private Processor processor;
    private GeneratorOptions options;

    @Before
    public void init(){
        this.processor = new RuleProjectProcessor();
        this.options = new GeneratorOptions();

        this.options.setOutputDir("testOutput");
        processor.setOptions(options);
    }

    @After
    public void cleanup(){
        FileUtil.deleteFolder(options.getOutputDir());
    }

    @Test
    public void generateAppTest() throws Exception{

        String projectPath = this.getClass().getResource("/HelloWorld/Hello Main Service").toURI().getPath();
        String xomPath = this.getClass().getResource("/hello-xom-1.0.0.jar").getFile();
        String depName = "simple dep";

        options.setRuleAppProjectPath(projectPath);
        options.setXomJarPath(xomPath);
        options.setRuleAppDeploymentName(depName);

        processor.process();

        Path outputRuleApp = Paths.get(options.getRuleAppJarPath());

        assertTrue(Files.exists(outputRuleApp));
    }

    @Test (expected = RuntimeException.class)
    public void wrongProjectPathTest(){
        String xomPath = this.getClass().getResource("/hello-xom-1.0.0.jar").getFile();
        String depName = "simple dep";

        options.setRuleAppProjectPath("wrong/path/salut/");
        options.setXomJarPath(xomPath);
        options.setRuleAppDeploymentName(depName);

        processor.process();
    }

    @Test(expected = NoRuleAppGeneratedException.class)
    public void wrongDeploymentNameTest() throws Exception{
        String projectPath = this.getClass().getResource("/HelloWorld/Hello Main Service").toURI().getPath();
        String xomPath = this.getClass().getResource("/hello-xom-1.0.0.jar").getFile();
        String depName = "no such dep";

        options.setRuleAppProjectPath(projectPath);
        options.setXomJarPath(xomPath);
        options.setRuleAppDeploymentName(depName);

        processor.process();
    }

    @Test(expected = RuntimeException.class)
    public void wrongXomPathTest() throws Exception{
        String projectPath = this.getClass().getResource("/HelloWorld/Hello Main Service").toURI().getPath();
        String xomPath = "nosuchxom.jar";
        String depName = "simple dep";

        options.setRuleAppProjectPath(projectPath);
        options.setXomJarPath(xomPath);
        options.setRuleAppDeploymentName(depName);

        processor.process();
    }

    @Test
    public void emptyOption(){
        this.processor.setOptions(new GeneratorOptions());
        this.processor.process();
    }
}

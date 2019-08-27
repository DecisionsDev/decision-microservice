package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.AdditionalProperties;
import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.exception.MissingLibException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.ibm.decisionMicroservice.utils.FileUtil;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ODMLibProcessorTest {
    private Processor processor;
    private GeneratorOptions options;
    private String odmFolderPath;
    private String outputdir = "test/output";

    @Before
    public void init() throws Exception{
        this.processor = new ODMLibProcessor();
        this.options = new GeneratorOptions();
        processor.setOptions(options);
        this.options.setOutputDir(this.outputdir);
        this.odmFolderPath = this.getClass().getResource("/odmlib").getPath();

        Map<String,String> vars = new HashMap<>();
        vars.put(AdditionalProperties.ENV_ODMLIB,this.odmFolderPath);
        setEnv(vars);
    }

    @After
    public void cleanup(){
        FileUtil.deleteFolder("test");
    }

    @Test
    public void copyTest() throws Exception{
        processor.process();

        assertTrue(FileUtil.compareFile(
                this.odmFolderPath + File.separator + AdditionalProperties.J2EE_CONNECTOR,
                this.options.getOutputDir() + File.separator + "lib" + File.separator + AdditionalProperties.J2EE_CONNECTOR));
        assertTrue(FileUtil.compareFile(
                this.odmFolderPath + File.separator + AdditionalProperties.JRULES_ENGINE,
                this.options.getOutputDir() + File.separator + "lib" + File.separator + AdditionalProperties.JRULES_ENGINE));
        assertTrue(FileUtil.compareFile(
                this.odmFolderPath + File.separator + AdditionalProperties.JRULES_RES_MANAGE_TOOLS,
                this.options.getOutputDir() + File.separator + "lib" + File.separator + AdditionalProperties.JRULES_RES_MANAGE_TOOLS));
        assertTrue(FileUtil.compareFile(
                this.odmFolderPath + File.separator + AdditionalProperties.JRULES_RES_EXECUTION,
                this.options.getOutputDir() + File.separator + "lib" + File.separator + AdditionalProperties.JRULES_RES_EXECUTION));
    }

    @Test(expected = MissingLibException.class)
    public void missingFileTest() throws Exception{
        Map<String,String> env = new HashMap<>();
        env.put(AdditionalProperties.ENV_ODMLIB,"./");
        setEnv(env);

        processor.process();
    }

    @Test
    public void noEnvTest() throws Exception{
        setEnv(new HashMap<>());
        processor.process();

        assertFalse(FileUtil.checkFile(this.options.getOutputDir() + File.separator + "lib" + File.separator + AdditionalProperties.JRULES_RES_MANAGE_TOOLS));
        assertFalse(FileUtil.checkFile(this.options.getOutputDir() + File.separator + "lib" + File.separator + AdditionalProperties.JRULES_RES_EXECUTION));
        assertFalse(FileUtil.checkFile(this.options.getOutputDir() + File.separator + "lib" + File.separator + AdditionalProperties.JRULES_ENGINE));
        assertFalse(FileUtil.checkFile(this.options.getOutputDir() + File.separator + "lib" + File.separator + AdditionalProperties.J2EE_CONNECTOR));
    }

    @Test
    public void emptyOption(){
        this.processor.setOptions(new GeneratorOptions());
        this.processor.process();
    }

    //set env variable
    // test purpose only
    //https://stackoverflow.com/questions/318239/how-do-i-set-environment-variables-from-java
    private static void setEnv(Map<String, String> newenv) throws Exception {
        try {
            Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
            Field theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment");
            theEnvironmentField.setAccessible(true);
            Map<String, String> env = (Map<String, String>) theEnvironmentField.get(null);
            env.putAll(newenv);
            Field theCaseInsensitiveEnvironmentField = processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
            theCaseInsensitiveEnvironmentField.setAccessible(true);
            Map<String, String> cienv = (Map<String, String>)     theCaseInsensitiveEnvironmentField.get(null);
            cienv.putAll(newenv);
        } catch (NoSuchFieldException e) {
            Class[] classes = Collections.class.getDeclaredClasses();
            Map<String, String> env = System.getenv();
            for(Class cl : classes) {
                if("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
                    Field field = cl.getDeclaredField("m");
                    field.setAccessible(true);
                    Object obj = field.get(env);
                    Map<String, String> map = (Map<String, String>) obj;
                    map.clear();
                    map.putAll(newenv);
                }
            }
        }
    }
}

package codegenClient;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.codeGen.GeneratorOrchestrator;
import com.ibm.decisionMicroservice.codeGen.factory.Frameworks;
import com.ibm.decisionMicroservice.codeGen.factory.OrchestratorFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.decisionMicroservice.exception.MissingFileException;
import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CommandLine.Command(description = "Generate container from given input",mixinStandardHelpOptions = true)
public class GeneratorCLI implements Callable<Void> {

    private static Logger logger = LoggerFactory.getLogger("CLI");

    @Option(names = { "-i" ,"--input"},
            description = "path to json formatted file containing parameters ")
    private String inputOptionPath;

    @Option(names = {"-a","--openapi"},
    description = "path to openApi specification")
    private String openApiPath;

    @Option(names = {"-o","--outputdir"},
    description = "directory where the files will be generated")
    private String outputDirectory;

    @Option(names = {"-x","--xom"},
    description = "path to the xom artifact")
    private String xomPath;

    @Option(names = {"-r","--ruleapp"},
    description = "path to the ruleapp artifact")
    private String ruleAppPath;

    @Option(names = {"-s","--ruleset"},
    description = "rule set path")
    private String ruleSetPath;

    @Option(names = {"-sa","--rulesetarchive"},
    description = "path to the ruleset archive file")
    private String rulesetArchivePath;

    @Option(names = {"-rp","--ruleproject"},
            description = "path to the rule project folder")
    private String ruleProject;

    @Option(names = {"-dep","--deploymentname"},
            description = "name of the deployment to use in order to build rule project")
    private String deploymentName;

    @Option(names = {"-p","--properties"},
    description = "additional properties")
    private Map<String,String> additionalProperties;

    @Option(names = {"-f","--framework"},
    description = "framework used by the container to run the ruleset, SpringBoot by default")
    private String framework = Frameworks.SPRINGBOOT.getName();

    private Map<String,String> localProperties;

    public GeneratorCLI(){
        this.additionalProperties = new HashMap<>();
    }

    public static void main(String[] args) throws Exception {
        try {
            logger.info("Starting from dir : " + System.getProperty("user.dir"));

            InputFileOption fileOption = new InputFileOption();
            CommandLine fileInput = new CommandLine(fileOption);
            fileInput.setUnmatchedArgumentsAllowed(true);
            fileInput.parse(args);

            GeneratorCLI cli;
            if (fileOption.getInputFile() != null) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                Path path = Paths.get(fileOption.getInputFile());

                if (!Files.exists(path)) {
                    throw new MissingFileException(path.toString());
                }

                JsonNode node = mapper.readTree(path.toFile());
                cli = mapper.convertValue(node, GeneratorCLI.class);
                cli.localProperties = cli.additionalProperties;
            } else {
                cli = new GeneratorCLI();
            }

            CommandLine.call(cli, args);
        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public Void call() throws Exception {
        OrchestratorFactory factory = new OrchestratorFactory();
        GeneratorOrchestrator generator = factory.makeOrchestrator(this.toOptions());
        generator.run();

        return null;
    }

    private GeneratorOptions toOptions(){
        if (this.localProperties != null){
            this.localProperties.putAll(this.additionalProperties);
            this.additionalProperties = this.localProperties;
        }

        GeneratorOptions opt = new GeneratorOptions();
        opt.setRuleSetPathArchive(this.rulesetArchivePath);
        opt.setRuleSetPath(this.ruleSetPath);
        opt.setRuleAppJarPath(this.ruleAppPath);
        opt.setXomJarPath(this.xomPath);
        opt.setRuleAppProjectPath(this.ruleProject);
        opt.setRuleAppDeploymentName(this.deploymentName);
        opt.setFramework(this.framework);

        if (this.outputDirectory == null){
            opt.setOutputDir("default");
        } else {
            opt.setOutputDir(this.outputDirectory);
        }
        opt.setSwaggerPath(this.openApiPath);
        opt.setAdditionalProperties(this.additionalProperties);
        return opt;
    }

    public String getInputOptionPath() {
        return inputOptionPath;
    }

    public void setInputOptionPath(String inputOptionPath) {
        this.inputOptionPath = inputOptionPath;
    }

    public String getOpenApiPath() {
        return openApiPath;
    }

    public void setOpenApiPath(String openApiPath) {
        this.openApiPath = openApiPath;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public String getXomPath() {
        return xomPath;
    }

    public void setXomPath(String xomPath) {
        this.xomPath = xomPath;
    }

    public String getRuleAppPath() {
        return ruleAppPath;
    }

    public void setRuleAppPath(String ruleAppPath) {
        this.ruleAppPath = ruleAppPath;
    }

    public String getRuleSetPath() {
        return ruleSetPath;
    }

    public void setRuleSetPath(String ruleSetPath) {
        this.ruleSetPath = ruleSetPath;
    }

    public String getRulesetArchivePath() {
        return rulesetArchivePath;
    }

    public void setRulesetArchivePath(String rulesetArchivePath) {
        this.rulesetArchivePath = rulesetArchivePath;
    }

    public Map<String, String> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, String> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public String getRuleProject() {
        return ruleProject;
    }

    public void setRuleProject(String ruleProject) {
        this.ruleProject = ruleProject;
    }

    public String getDeploymentName() {
        return deploymentName;
    }

    public void setDeploymentName(String deploymentName) {
        this.deploymentName = deploymentName;
    }
}

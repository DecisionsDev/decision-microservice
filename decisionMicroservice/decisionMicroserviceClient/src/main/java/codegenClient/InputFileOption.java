package codegenClient;

import picocli.CommandLine;

public class InputFileOption{
    @CommandLine.Option(names = { "-i" ,"--input"})
    private String inputFile;

    public String getInputFile() {
        return inputFile;
    }
}
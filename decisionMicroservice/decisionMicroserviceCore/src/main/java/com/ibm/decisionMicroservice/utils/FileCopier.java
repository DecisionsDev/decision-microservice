package com.ibm.decisionMicroservice.utils;

import com.ibm.decisionMicroservice.exception.MissingFileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class FileCopier {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<String> createdFiles;

    public FileCopier(){
        createdFiles = new ArrayList<>();
    }

    public void cleanup() {
        List<String> createdFilesTemp = new ArrayList<>(this.createdFiles);
        for (String filename : createdFilesTemp){
            try {
                Path delete = Paths.get(filename);
                Files.delete(delete);
                this.createdFiles.remove(filename);
            } catch (IOException e){
                logger.info("could not delete file : " + filename);
            }
        }
    }

    public void move(String filename, String destination) {
        Path from = Paths.get(filename);
        Path to = Paths.get(destination);

        if(!Files.exists(from)){
            throw new MissingFileException(filename);
        }
        try {
            if (!Files.exists(to.getParent())) {
                Files.createDirectories(to.getParent());
            }

            Files.move(from, to, StandardCopyOption.REPLACE_EXISTING);
            this.createdFiles.add(destination);
        } catch (IOException e){
            throw new MissingFileException(filename,e);
        }
    }

    public void copy(String filename, String destination) {
        try {
            Path from = Paths.get(filename);
            Path to = Paths.get(destination);
            if (!Files.exists(to.getParent())) {
                Files.createDirectories(to.getParent());
            }
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
            this.createdFiles.add(destination);
        } catch (IOException e) {
            throw new MissingFileException(filename,e);
        }
    }

    public void create(String filename, byte[] content){
        try {
            Path to = Paths.get(filename);
            if (!Files.exists(to.getParent())) {
                Files.createDirectories(to.getParent());
            }
            Files.createFile(to);
            Files.write(to,content);
        } catch (IOException e) {
            throw new MissingFileException(filename,e);
        }
    }

}

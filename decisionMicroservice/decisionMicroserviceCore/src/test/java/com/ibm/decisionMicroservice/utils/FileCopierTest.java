package com.ibm.decisionMicroservice.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.Assert.*;
import static com.ibm.decisionMicroservice.utils.FileUtil.checkFile;
import static com.ibm.decisionMicroservice.utils.FileUtil.createFile;;

public class FileCopierTest {
    private FileCopier copier;

    @Before
    public void init(){
        copier = new FileCopier();
    }

    @After
    public void cleanup() throws IOException{
        copier.cleanup();
        FileUtil.deleteFolder("test");
    }

    @Test
    public void moveTest() throws IOException{
        String filename = "test.txt";
        String from = "./test/test/" + filename;
        String to = "./test/test/new/" + filename;

        createFile(from);

        copier.move(from,to);

        assertTrue(checkFile(to));
    }

    @Test(expected = RuntimeException.class)
    public void moveNonExistingFile(){
        copier.move("azeazeazeaz","azeazeze");
    }

    @Test
    public void cleanupTest() throws IOException{
        String filename = "test.txt";
        String from = "./test/test/" + filename;
        String copyTo = "./test/test/new/test/" + filename;
        String to = "./test/test/new/" + filename;

        createFile(from);
        copier.copy(from,copyTo);
        copier.move(from,to);
        copier.cleanup();

        assertFalse(checkFile(to));
        assertFalse(checkFile(copyTo));
    }

    @Test
    public void copyTest() throws Exception{
        String filename = "test.txt";
        String from = "./test/test/" + filename;
        String to = "./test/test/new/" + filename;

        createFile(from);
        copier.copy(from,to);
        assertTrue(checkFile(from));
        assertTrue(checkFile(to));
    }

    @Test(expected = RuntimeException.class)
    public void copyNonExistingFile(){
        copier.copy("azeazeazeaz","azeazeze");
    }

    @Test
    public void removeExistingCopy() throws Exception{
        String filename = "test.txt";
        String from = "./test/test/" + filename;
        String to = "./test/test/new/" + filename;

        createFile(from);
        createFile(to);
        copier.copy(from,to);
        assertTrue(checkFile(from));
        assertTrue(checkFile(to));
    }

    @Test
    public void removeExistingMove() throws Exception{
        String filename = "test.txt";
        String from = "./test/test/" + filename;
        String to = "./test/test/new/" + filename;

        createFile(from);
        createFile(to);
        copier.move(from,to);

        assertTrue(checkFile(to));
    }

    @Test
    public void createTest() throws Exception{
        Path created = Paths.get("./test/ouput.txt");

        InputStream toCopy = this.getClass().getResourceAsStream("/miniloan-xom.jar");
        byte[] content = new byte[toCopy.available()];
        toCopy.read(content);

        this.copier.create(created.toString(),content);

        byte[] readContent = Files.readAllBytes(created);

        assertTrue(Arrays.equals(content,readContent));
    }



}

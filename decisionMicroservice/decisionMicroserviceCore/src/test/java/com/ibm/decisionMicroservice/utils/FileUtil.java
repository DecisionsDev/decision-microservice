package com.ibm.decisionMicroservice.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.assertTrue;

public class FileUtil {

    public static boolean checkFile(String path){
        File file = new File(path);
        return file.exists();
    }

    public static void createFile(String path) throws IOException {
        File targetFile = new File(path);
        File parent = targetFile.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }
        targetFile.createNewFile();
    }

    public static void deleteFolder(String folderPath){
        try {
            Path path = Paths.get(folderPath);
            Files.walk(path)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (Exception e){

        }
    }

    public static boolean compareFile(String file1,String file2) throws IOException{
        byte[] content1 = Files.readAllBytes( Paths.get(file1));
        byte[] content2 = Files.readAllBytes(Paths.get(file2));
        return Arrays.equals(content1,content2);
    }
}

package com.farhad.example.functional02.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDataFileWriter implements DataFileWriter {

    private static String HOME = System.getProperty("user.home");
    private final String TEMP_DIR = "target";

    @Override
    public File writeDataFile(String contents, String location) {

        Path filePath = Paths.get(TEMP_DIR + "/" + location);
        filePath.toFile().deleteOnExit();
        try {
            Files.createFile(filePath);
            byte[] strToByte = contents.getBytes();
            Files.write(filePath.toFile().toPath(), strToByte);
    
            return filePath.toFile().getAbsoluteFile() ;    
    
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}

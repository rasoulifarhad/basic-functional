package com.farhad.example.functional02.remote.inheritance;

import lombok.Getter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import com.farhad.example.functional02.common.DataFileException;

@Getter
public class FileDataFileMetadata extends DataFileMetadata {

    private final File file;

    public FileDataFileMetadata( long customerId, String type, File file ) {
        super(customerId, type);
        this.file = file ;
    }


    @Override
    public String loadContents() {

        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException ex) {
           throw new DataFileException(ex)    ;   
        }

    }
    
}

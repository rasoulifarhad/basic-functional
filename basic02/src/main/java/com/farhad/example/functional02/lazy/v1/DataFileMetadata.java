package com.farhad.example.functional02.lazy.v1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import com.farhad.example.functional02.lazy.DataFileUnavailableException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataFileMetadata {
    
    private long customerId ;
    private String type ;
    private File file ;
    private String contents;

    public void loadContents() {

        try {
            contents = loadFromFile() ;
        } catch (IOException ex) {
           throw new DataFileUnavailableException(ex)    ;   
        }
    }

    private String  loadFromFile() throws IOException {

        return new String(Files.readAllBytes(file.toPath()));
    }
}

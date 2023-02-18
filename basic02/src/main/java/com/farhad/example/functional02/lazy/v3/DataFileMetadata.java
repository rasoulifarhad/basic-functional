package com.farhad.example.functional02.lazy.v3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.farhad.example.functional02.lazy.DataFileUnavailableException;

import lombok.Getter;
import lombok.Setter;

/**
 * NOTE: 
 *      We can solve the performance problem of loading every file into memory on object creation by moving the call 
 *      to load to an overloaded get method
 */
@Getter
@Setter
public class DataFileMetadata {
    
    private long customerId ;
    private String type ;
    private File file ;
    private String contents ;

    public String getContents() {
        if (contents == null)
            loadContents() ;
        return contents;
    }
    private void loadContents() {

        try {
            contents =  loadFromFile() ;
        } catch (IOException ex) {
           throw new DataFileUnavailableException(ex)    ;   
        }
    }

    private String  loadFromFile() throws IOException {

        return new String(Files.readAllBytes(file.toPath()));
    }
}

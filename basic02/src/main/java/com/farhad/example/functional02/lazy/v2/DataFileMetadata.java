package com.farhad.example.functional02.lazy.v2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.farhad.example.functional02.lazy.DataFileUnavailableException;

import lombok.Getter;
import lombok.Setter;

/**
 * NOTE: 
 *      it will also impose a performance penalty as we now load every File into memory when it’s metadata Object is created.
 */
@Getter
@Setter
public class DataFileMetadata {
    
    private long customerId ;
    private String type ;
    private File file ;
    private String contents = loadContents();

    private String loadContents() {

        try {
            return  loadFromFile() ;
        } catch (IOException ex) {
           throw new DataFileUnavailableException(ex)    ;   
        }
    }

    private String  loadFromFile() throws IOException {

        return new String(Files.readAllBytes(file.toPath()));
    }
}

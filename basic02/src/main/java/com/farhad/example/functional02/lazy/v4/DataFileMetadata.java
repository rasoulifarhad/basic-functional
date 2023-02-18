package com.farhad.example.functional02.lazy.v4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.farhad.example.functional02.lazy.DataFileUnavailableException;
import java.util.function.Supplier;
import lombok.Getter;
import lombok.Setter;

/**
 * NOTE: 
 *      Using Supplier : the lazy interface
 *      Suppplier<String> contents = this::loadContents;
 * BUT:
 *      But.. oh no! now we’ve lost our caching!
 */
@Getter
@Setter
public class DataFileMetadata {
    
    private long customerId ;
    private String type ;
    private File file ;
    private Supplier<String> contents = this::loadContents;

    public String getContents() {
        return contents.get();
    }
    private String loadContents() {

        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException ex) {
           throw new DataFileUnavailableException(ex)    ;   
        }
    }
}

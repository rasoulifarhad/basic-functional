package com.farhad.example.functional02.remote;

import java.io.File;
import java.io.IOException;
import  lombok.With;
import java.nio.file.Files;

import com.farhad.example.functional02.common.DataFileException;

import cyclops.control.Eval;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *  We have our DataFileMetadata class that represents a Data File stored in some location for a customer.
 * 
 *  Not just local files
 * 
 * A new requirement. Customers can manage their own files on their own public facing Webservers. We need to 
 * support loading data from a supplied URL.
 * 
 * If we add just this one additional field to our DataFileMetadata class, it will result in significantly more 
 * complexity making the code for that class harder to reason about.
 * 
 *     private final URL url;
 */
@Getter
@AllArgsConstructor
public class DataFileMetadata {
    
    private final long customerId ;
    private final String type ;
    @With
    private final File file ;
    
    private final Eval<String> contents = Eval.later( this::loadContents );

    public String getContents() {
        return contents.get();
    }
    private String loadContents() {

        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException ex) {
           throw new DataFileException(ex)    ;   
        }
    }
}

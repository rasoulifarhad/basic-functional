package com.farhad.example.functional02.composition.v1;

import java.io.File;
import java.io.IOException;
import  lombok.With;
import java.nio.file.Files;

import com.farhad.example.functional02.common.DataFileException;

import cyclops.control.Eval;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 
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

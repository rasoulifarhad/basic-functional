package com.farhad.example.functional02.remote.badd;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import  lombok.With;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Collectors;

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
 *    - only file or url should have a value at any given time
 *    - The other one should be null
 *    - but both should not be null.
 * 
 * Can other Functional Data types help?
 * 
 * There is a functional data type that will allow us to represent this relationship accurately : Either. 
 * 
 * Either represents a value that can be of one two defined types.
 * 
 *    Either<URL,File>
 * 
 * If this is the extent of the changes or differences between our data model for local files and remote 
 * URLs this may be an acceptable solution.
 * 
 * Inheritance too the rescue!
 * 
 * A more common solution in traditional Java is to leverage — inheritance! We can make DataFileMetadata an abstract 
 * base class with two different implementations, one for local files and one for remote URLs.
 */
@Getter
@AllArgsConstructor
public class DataFileMetadata {
    
    private final long customerId ;
    private final String type ;
    @With
    private final File file ;
    @With
    private final URL url ;
    
    private final Eval<String> contents = Eval.later( this::loadContents );

    public String loadContents() {

        try {

            if ( file != null) {

                return new String(Files.readAllBytes(file.toPath()));
            }
            
            if (url != null) {
                
                URLConnection conn = url.openConnection();

                try ( BufferedReader in = new BufferedReader( new InputStreamReader( conn.getInputStream(), StandardCharsets.UTF_8 ) ) ) {

                    return in.lines().collect(Collectors.joining("\n"));

                }
            }
            throw new DataFileException("No File or URL set");
                
        } catch (IOException ex) {

           throw new DataFileException(ex)    ;   

        }
    }
}
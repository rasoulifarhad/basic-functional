package com.farhad.example.functional02.lazy.v5;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.farhad.example.functional02.lazy.DataFileUnavailableException;
import java.util.function.Supplier;
import lombok.Getter;
import lombok.Setter;

import static com.farhad.example.functional02.lazy.v5.Memoization.memoizeSupplier; 

/**
 * Memoization : to the rescue!
 * 
 * Memoization is closely related to laziness in functional programming, and refers to the ability to cache and 
 * resuse lazily computed values.
 * 
 * It is pretty straightforward to implement Memoization for Suppliers. For example using ConcurrentHashMap in 
 * Java we could define a Memoization function for Suppliers
 * 
 */
@Getter
@Setter
public class DataFileMetadata {
    
    private long customerId ;
    private String type ;
    private File file ;
    
    private Supplier<String> contents = memoizeSupplier(this::loadContents);

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

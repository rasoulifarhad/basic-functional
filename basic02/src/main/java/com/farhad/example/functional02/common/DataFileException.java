package com.farhad.example.functional02.common;

public class DataFileException extends RuntimeException {

    public DataFileException(Throwable cause) {
        super(cause);
    }

    public DataFileException(String message) {
        super(message);
    }
    
    
}
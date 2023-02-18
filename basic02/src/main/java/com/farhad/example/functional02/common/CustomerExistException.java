package com.farhad.example.functional02.common;

public class CustomerExistException extends RuntimeException {

    public CustomerExistException() {
        this("Customer exist.");
    }

    public CustomerExistException(String message) {
        super(message);
    }
    
}

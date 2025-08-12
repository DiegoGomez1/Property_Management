package com.example.Property.Management.exception;

public class BuildingNotFoundException extends RuntimeException {
    
    public BuildingNotFoundException(String message) {
        super(message);
    }
    
    public BuildingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
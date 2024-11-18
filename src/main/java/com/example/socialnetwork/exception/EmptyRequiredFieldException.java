package com.example.socialnetwork.exception;

public class EmptyRequiredFieldException extends RuntimeException {
    public EmptyRequiredFieldException(String fieldName) {
        super("Field " + fieldName + " is required");
    }
}

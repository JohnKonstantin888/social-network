package com.example.socialnetwork.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String paramName, String paramValue) {
        super("User with " + paramName + " = '" + paramValue + "' not found");
    }
}

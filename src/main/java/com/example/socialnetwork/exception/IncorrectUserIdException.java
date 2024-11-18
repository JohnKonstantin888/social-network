package com.example.socialnetwork.exception;

public class IncorrectUserIdException extends RuntimeException {
    public IncorrectUserIdException(String userId) {
        super("Incorrect user ID: " + userId);
    }
}

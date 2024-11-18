package com.example.socialnetwork.exception;

public class IncorrectRequestException extends RuntimeException {
    public IncorrectRequestException() {
        super("Incorrect request for create/update user");
    }
}

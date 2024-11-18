package com.example.socialnetwork.exception;

public class UserWithLoginAlreadyExistsException extends RuntimeException {
    public UserWithLoginAlreadyExistsException(String loginEmail) {
        super("User with login " + loginEmail + " already exists");
    }
}

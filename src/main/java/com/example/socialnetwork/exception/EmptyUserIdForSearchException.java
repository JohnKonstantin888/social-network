package com.example.socialnetwork.exception;

public class EmptyUserIdForSearchException extends RuntimeException {
    public EmptyUserIdForSearchException() {
        super("Empty user id for search");
    }
}

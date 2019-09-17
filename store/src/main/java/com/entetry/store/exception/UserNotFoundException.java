package com.entetry.store.exception;

public class UserNotFoundException extends MyResourceNotFoundException {
    public UserNotFoundException() {
        this("User not found");
    }

    public UserNotFoundException(String message) {
        super(message);

    }
}
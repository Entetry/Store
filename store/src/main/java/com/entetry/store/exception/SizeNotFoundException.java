package com.entetry.store.exception;

public class SizeNotFoundException extends MyResourceNotFoundException {
    public SizeNotFoundException() {
        this("Size not found");
    }

    public SizeNotFoundException(String message) {
        super(message);

    }
}
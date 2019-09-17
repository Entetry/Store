package com.entetry.store.exception;

public class AuthorityNotFoundException extends MyResourceNotFoundException{
    public AuthorityNotFoundException() {
        this("Authority not found");
    }

    public AuthorityNotFoundException(String message) {
        super(message);

    }
}
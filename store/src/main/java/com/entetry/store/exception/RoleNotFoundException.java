package com.entetry.store.exception;

public class RoleNotFoundException extends MyResourceNotFoundException {
    public RoleNotFoundException() {
        this("Role not found");
    }

    public RoleNotFoundException(String message) {
        super(message);

    }
}
package com.entetry.store.exception;

public class DesignerNotFoundException extends MyResourceNotFoundException {
    public DesignerNotFoundException() {
        this("Designer not found");
    }

    public DesignerNotFoundException(String message) {
        super(message);
    }
}

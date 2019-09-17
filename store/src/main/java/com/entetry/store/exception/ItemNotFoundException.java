package com.entetry.store.exception;

public class ItemNotFoundException extends MyResourceNotFoundException {
    public ItemNotFoundException() {
        this("Item not found");
    }

    public ItemNotFoundException(String message) {
        super(message);
    }
}

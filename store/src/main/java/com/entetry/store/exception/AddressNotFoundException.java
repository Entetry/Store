package com.entetry.store.exception;

public class AddressNotFoundException extends MyResourceNotFoundException {
    public AddressNotFoundException() {
        this("Address not found");
    }

    public AddressNotFoundException(String message) {
        super(message);
    }
}

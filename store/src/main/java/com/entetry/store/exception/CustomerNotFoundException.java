package com.entetry.store.exception;

public class CustomerNotFoundException extends MyResourceNotFoundException {
    public CustomerNotFoundException() {
        this("Customer not found");
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }
}

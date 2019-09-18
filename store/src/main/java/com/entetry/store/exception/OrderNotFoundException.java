package com.entetry.store.exception;

public class OrderNotFoundException extends MyResourceNotFoundException {
    public OrderNotFoundException() {
        this("Order not found");
    }

    public OrderNotFoundException(String message) {
        super(message);

    }
}
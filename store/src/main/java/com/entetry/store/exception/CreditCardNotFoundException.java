package com.entetry.store.exception;

public class CreditCardNotFoundException  extends MyResourceNotFoundException {
    public CreditCardNotFoundException() {
        this("Credit card not found");
    }

    public CreditCardNotFoundException(String message) {
        super(message);
    }
}

package com.entetry.store.exception;

public class BankAccountNotFoundException extends MyResourceNotFoundException {
    public BankAccountNotFoundException() {
        this("Bank account not found");
    }

    public BankAccountNotFoundException(String message) {
        super(message);
    }
}

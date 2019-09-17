package com.entetry.store.exception;

public class NotEnoughMoneyException extends MyResourceNotFoundException {
    public NotEnoughMoneyException() {
        this("Not enough money");
    }

    public NotEnoughMoneyException(String message) {
        super(message);

    }
}
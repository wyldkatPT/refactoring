package com.celfocus.training.exceptions;

public class ShoppingCartNotFoundException extends Throwable {
    public ShoppingCartNotFoundException(String message) {
        super(message);
    }
}

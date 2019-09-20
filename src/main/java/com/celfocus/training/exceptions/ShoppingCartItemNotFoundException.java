package com.celfocus.training.exceptions;

public class ShoppingCartItemNotFoundException extends Throwable {
    public ShoppingCartItemNotFoundException(String message) {
        super(message);
    }
}

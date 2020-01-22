package com.celfocus.training.models;

import java.util.List;

public class ShoppingCart {

    private User user;

    private List<ShoppingCartItem> cartItems;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ShoppingCartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<ShoppingCartItem> cartItems) {
        this.cartItems = cartItems;
    }
}

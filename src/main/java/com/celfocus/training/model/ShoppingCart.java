package com.celfocus.training.model;

import java.util.List;

public class ShoppingCart {

    private User user;

    private List<ShoppingCartItem> items;

    public ShoppingCart(User user, List<ShoppingCartItem> items) {
        this.user = user;
        this.items = items;
    }

    public ShoppingCart() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartItem> items) {
        this.items = items;
    }
}

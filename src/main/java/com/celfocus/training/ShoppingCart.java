package com.celfocus.training;

import java.util.List;

public class ShoppingCart {
    public User user;
    public List<ShoppingCartItem> items;

    public ShoppingCart() {
        this.user = user;
        this.items = items;
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

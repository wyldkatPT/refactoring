package com.celfocus.training;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private User user;

    private List<ShoppingCartItem> items;

    public ShoppingCart(User user){
        this.user = user;
        this.items = new ArrayList<>();
    }

    public ShoppingCart(User user, List<ShoppingCartItem> items) {
        this.user = user;
        this.items = items;
    }

    public void addCartItem(ShoppingCartItem item){
        items.add(item);
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

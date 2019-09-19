package com.celfocus.training;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private User user;

    private ArrayList<ShoppingCartItem> items;

    public ShoppingCart(User user){
        this.user = user;
        this.items = new ArrayList<ShoppingCartItem>();
    }

    public ShoppingCart(User user, ArrayList<ShoppingCartItem> items) {
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

    public ArrayList<ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<ShoppingCartItem> items) {
        this.items = items;
    }
}

package com.celfocus.training.impl;

import com.celfocus.training.models.ShoppingCartItem;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartItemImpl {

    private List<ShoppingCartItem> items = new ArrayList<>();

    public ShoppingCartItem findShoppingCartItem(String name) {

        ShoppingCartItem scif = null;
        for (ShoppingCartItem s : items) {
            if (name == s.getItem().getName()) {
                scif = s;
            }
        }
        return scif;
    }
}

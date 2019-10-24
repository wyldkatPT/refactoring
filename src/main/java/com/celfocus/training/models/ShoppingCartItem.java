package com.celfocus.training.models;

import com.celfocus.training.Saver;

public class ShoppingCartItem {
    public ItemInfo item;
    public int quantity;
    public double discount;

    public ShoppingCartItem(ItemInfo info, int quantity, double discount) {
        this.item = info;
        this.quantity = quantity;
        this.discount = discount;
    }

    public ItemInfo getItem() {
        return item;
    }

    public void setItem(ItemInfo info) {
        this.item = info;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

package com.celfocus.training.util;

public class ShoppingCartItem {
    public ItemInfo item;

    public int quantity;

    public double discount;

    public ShoppingCartItem(ItemInfo item, int qt, double discount) {
        this.item = item;
        this.quantity = qt;
        this.discount = discount;
    }
    public ShoppingCartItem(){

    }

    public ItemInfo getItem() {
        return item;
    }

    public void setItem(ItemInfo item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}

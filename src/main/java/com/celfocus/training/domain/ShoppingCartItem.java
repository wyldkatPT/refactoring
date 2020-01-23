package com.celfocus.training.domain;

import java.util.Optional;

public class ShoppingCartItem {

    private ItemInfo itemInfo;

    private int quantity;

    private double discount;


    public ShoppingCartItem(ItemInfo itemInfo, int quantity, double discount){
        this.setItemInfo(itemInfo);
        this.setQuantity(quantity);
        this.setDiscount(discount);
    }

    public ItemInfo getItemInfo() {
        return itemInfo;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setItemInfo(ItemInfo itemInfo) {
        this.itemInfo = itemInfo;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    ////////////////////////////////////////////// FIND METHODS ////////////////////////////////////////////////////////

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }

}

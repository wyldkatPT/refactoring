package com.celfocus.training.models;

public class ShoppingCartItem {
    private Product product;

    private int amount;

    private double discount;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public ShoppingCartItem(Product product, int amount, double discount) {
        this.product = product;
        this.amount = amount;
        this.discount = discount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}

package com.celfocus.training.shoppingcart;

import com.celfocus.training.item.ItemInfo;

public class ShoppingCartItem {
    public ItemInfo item;

    public int qt;

    public double discount;

    public ShoppingCartItem(ItemInfo item, int qt) {
        this.item = item;
        this.qt = qt;
    }
}

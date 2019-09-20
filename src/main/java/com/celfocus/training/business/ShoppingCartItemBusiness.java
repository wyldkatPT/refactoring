package com.celfocus.training.business;
import com.celfocus.training.model.ShoppingCartItem;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartItemBusiness {

    private List<ShoppingCartItem> items = new ArrayList<>();

    public ShoppingCartItem findShoppingCartItem(String name) {

        ShoppingCartItem shoppingCartItem = null;
            for (ShoppingCartItem s : items) {
                String itemName = s.getItem().getName();
                if (name == itemName) shoppingCartItem = s;
            }

        return shoppingCartItem;
    }

}

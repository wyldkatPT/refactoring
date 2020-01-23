package com.celfocus.training.core;

import com.celfocus.training.entity.User;
import com.celfocus.training.entity.cart.ShoppingCart;

import java.util.List;

public class ShoppingCartFinder implements IShoppingCart {

    @Override
    public ShoppingCart getShoppingCartByUser(User userFound, List<ShoppingCart> shoppingCarts) {
        ShoppingCart shoppingCartFound = null;
        for (ShoppingCart var : shoppingCarts) {
            if (var.getUser() == userFound) {
                shoppingCartFound = var;
            }
        }
        return shoppingCartFound;
    }
}

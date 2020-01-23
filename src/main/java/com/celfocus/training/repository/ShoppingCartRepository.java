package com.celfocus.training.repository;

import com.celfocus.training.model.ShoppingCart;
import com.celfocus.training.model.User;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartRepository {

    private final List<ShoppingCart> shoppingCarts;

    public ShoppingCartRepository() {
        shoppingCarts = new ArrayList<>();
    }

    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        shoppingCarts.add(shoppingCart);
        return shoppingCart;
    }

    public ShoppingCart getShoppingCartByUser(String userName) {
        return shoppingCarts.stream().filter(shoppingCart -> shoppingCart.getUser().getName().equals(userName)).findFirst().orElse(null);
    }
}

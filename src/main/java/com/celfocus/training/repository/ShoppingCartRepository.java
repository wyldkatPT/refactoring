package com.celfocus.training.repository;

import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.User;

import java.util.ArrayList;
import java.util.List;

class ShoppingCartRepository {

    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();

    private ShoppingCart createShoppingCart(User user) {
        ShoppingCart found = findShoppingCartByUser(user);
        ShoppingCart shoppingCart = new ShoppingCart();
        if (found == null) {
            shoppingCart.user = user;
            shoppingCarts.add(shoppingCart);
            return shoppingCart;
        }
        shoppingCart.user = user;
        shoppingCart.items = new ArrayList<>();
        shoppingCarts.add(shoppingCart);
        return shoppingCart;
    }

    ShoppingCart findShoppingCartByUser(User user) {
        ShoppingCart userShoppingCart = null;
        for (ShoppingCart shoppingCart : shoppingCarts) {
            if (shoppingCart.user == user) {
                userShoppingCart = shoppingCart;
            }
        }
        return userShoppingCart;
    }

}

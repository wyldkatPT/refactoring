package com.celfocus.training.services;

import com.celfocus.training.models.ShoppingCart;
import com.celfocus.training.models.User;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartItemService {

    private UserService userService;
    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();

    public static List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    };

    public boolean isUserAssociatedWithAShoppingCart(User user) {
        ShoppingCart shoppingCartWithUser = shoppingCarts.stream()
                .filter(shoppingCart -> user.equals(shoppingCart.getUser()))
                .findAny()
                .orElse(null);

        return shoppingCartWithUser != null;
    }

    public void saveOrUpdate(ShoppingCart shoppingCart) {
        int index = shoppingCarts.indexOf(shoppingCart);
        boolean doesShoppingCartExist = index != -1;

        if (!doesShoppingCartExist) {
            shoppingCarts.add(shoppingCart);
        } else {
            shoppingCarts.set(index, shoppingCart);
        }
    };

}

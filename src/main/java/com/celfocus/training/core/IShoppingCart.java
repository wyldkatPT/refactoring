package com.celfocus.training.core;

import com.celfocus.training.entity.User;
import com.celfocus.training.entity.cart.ShoppingCart;

import java.util.List;

public interface IShoppingCart {

    ShoppingCart getShoppingCartByUser(User userFound, List<ShoppingCart> shoppingCarts);
}

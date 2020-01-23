package com.celfocus.training.controller;

import com.celfocus.training.domain.ShoppingCart;
import com.celfocus.training.domain.User;
import com.celfocus.training.repository.shoppingcart.IShoppingCartRepository;

import java.util.ArrayList;
import java.util.Optional;

import static com.celfocus.training.repository.shoppingcart.inmemory.InMemoryShoppingCartRepository.shoppingCartList;

public class ShoppingCartController {

    private IShoppingCartRepository shoppingCartRepository;

    public ShoppingCartController(IShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public Optional<ShoppingCart> findShoppingCartByUser(User user) {
        return this.shoppingCartRepository.findByUser(user);
    }

    public ShoppingCart createShoppingCart(User user) {

        ShoppingCart shoppingCart = new ShoppingCart(user, new ArrayList<>());
        shoppingCartList.add(shoppingCart);
        return shoppingCart;

    }
}

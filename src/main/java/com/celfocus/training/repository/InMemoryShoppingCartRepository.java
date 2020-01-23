package com.celfocus.training.repository;

import com.celfocus.training.domain.ShoppingCart;
import com.celfocus.training.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryShoppingCartRepository implements IShoppingCartRepository{

    public static final List<ShoppingCart> shoppingCartList = new ArrayList<>();

    @Override
    public boolean insert(ShoppingCart shoppingCart) {
        return shoppingCartList.add(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> findByUser(User user) {
        return shoppingCartList.stream().filter(shoppingCart -> shoppingCart.getUser().equals(user)).findFirst();
    }
}

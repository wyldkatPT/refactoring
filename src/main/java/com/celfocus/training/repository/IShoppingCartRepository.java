package com.celfocus.training.repository;

import com.celfocus.training.domain.ShoppingCart;
import com.celfocus.training.domain.User;

import java.util.Optional;

public interface IShoppingCartRepository {
    boolean insert(ShoppingCart shoppingCart);

    Optional<ShoppingCart> findByUser(User user);
}

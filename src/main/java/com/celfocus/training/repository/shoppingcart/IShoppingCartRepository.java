package com.celfocus.training.repository.shoppingcart;

import com.celfocus.training.domain.ShoppingCart;
import com.celfocus.training.domain.User;
import com.celfocus.training.repository.IRepository;

import java.util.Optional;

public interface IShoppingCartRepository extends IRepository<ShoppingCart> {

    Optional<ShoppingCart> findByUser(User user);

}

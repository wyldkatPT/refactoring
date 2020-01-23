package com.celfocus.training.core;

import com.celfocus.training.entity.User;
import com.celfocus.training.entity.cart.ShoppingCart;

import java.util.Date;
import java.util.List;

public interface IUserSaver {
    User createUser(String name, Date birthDate, boolean isOlder, List<User> users, List<ShoppingCart> shoppingCarts);

    User updateUser(String name, Date birthDate, boolean isOlder, List<User> users, List<ShoppingCart> shoppingCarts);

    boolean findUserByName(String name, List<User> users);

    User getUserByName(String name, List<User> users);
}

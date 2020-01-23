package com.celfocus.training.core;

import com.celfocus.training.entity.User;
import com.celfocus.training.entity.cart.ShoppingCart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserSaver implements IUserSaver {
    private static final IShoppingCart iShoppingCart = new ShoppingCartFinder();

    @Override
    public User createUser(String name, Date birthDate, boolean isOlder, List<User> users, List<ShoppingCart> shoppingCarts) {
        User user = new User(name, birthDate, isOlder);
        users.add(user);
        ShoppingCart s = new ShoppingCart();
        s.setUser(user);
        s.setItems(new ArrayList<>());
        shoppingCarts.add(s);
        return user;
    }

    @Override
    public User updateUser(String name, Date birthDate, boolean isOlder, List<User> users, List<ShoppingCart> shoppingCarts) {
        User user = this.getUserByName(name, users);
        user.setBirthday(birthDate);
        user.setNotMinor(isOlder);
        ShoppingCart shoppingCartFound = iShoppingCart.getShoppingCartByUser(user, shoppingCarts);

        if (shoppingCartFound == null) {
            ShoppingCart s = new ShoppingCart();
            s.setUser(user);
            shoppingCarts.add(s);
        }
        users.add(user);
        return user;
    }

    @Override
    public boolean findUserByName(String name, List<User> users) {
        User userFound = null;
        for (User user : users) {
            if (user.getName().equals(name)) {
                userFound = user;
            }
        }
        return userFound != null;
    }

    @Override
    public User getUserByName(String name, List<User> users) {
        User userFound = null;
        for (User user : users) {
            if (user.getName().equals(name)) {
                userFound = user;
            }
        }
        return userFound;
    }
}

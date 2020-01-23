package com.celfocus.training;

import com.celfocus.training.core.IShoppingCart;
import com.celfocus.training.core.IUserSaver;
import com.celfocus.training.core.ShoppingCartFinder;
import com.celfocus.training.core.UserSaver;
import com.celfocus.training.entity.User;
import com.celfocus.training.entity.cart.ItemInfo;
import com.celfocus.training.entity.cart.ShoppingCart;
import com.celfocus.training.entity.cart.ShoppingCartItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Saver {

    private static final List<User> users = new ArrayList<>();
    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static final List<ItemInfo> items = new ArrayList<>();
    private static final IUserSaver iUserSaver = new UserSaver();
    private static final IShoppingCart iShoppingCart = new ShoppingCartFinder();

    public User saveOrUpdateUser(String name, Date birthDate, boolean isOlder) {
        if (iUserSaver.findUserByName(name, users)) {
            return iUserSaver.updateUser(name, birthDate, isOlder, users, shoppingCarts);
        } else {
            return iUserSaver.createUser(name, birthDate, isOlder, users, shoppingCarts);
        }
    }

    public void addItemToUser(String user, String itemName, int quantity) {
        User userFound = iUserSaver.getUserByName(user, users);
        if (Objects.isNull(userFound)) {
            return;
        }

        ShoppingCart shoppingCartFound = iShoppingCart.getShoppingCartByUser(userFound, shoppingCarts);
        if (Objects.isNull(shoppingCartFound)) {
            return;
        }

        ShoppingCartItem cartItemFound = this.getShoppingCartItem(itemName, shoppingCartFound);
        if (Objects.nonNull(cartItemFound)) {
            int quantitySum = cartItemFound.getQuantity() + quantity;
            cartItemFound.setQuantity(quantitySum);
        }
    }

    public void deleteUserOrNot(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.getName().equals(name)) {
                userFound = user;
            }
        }
        if (userFound != null) {
            users.remove(userFound);
        }
    }

    private ShoppingCartItem getShoppingCartItem(String itemName, ShoppingCart shoppingCartFound) {
        ShoppingCartItem cartItemFound = null;
        for (ShoppingCartItem s : shoppingCartFound.getItems()) {
            if (s.getItem().getName().equals(itemName)) {
                cartItemFound = s;
            }
        }
        return cartItemFound;
    }
} 
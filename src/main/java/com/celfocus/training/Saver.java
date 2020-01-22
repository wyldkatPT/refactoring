package com.celfocus.training;

import com.celfocus.training.entity.User;
import com.celfocus.training.entity.cart.ItemInfo;
import com.celfocus.training.entity.cart.ShoppingCart;
import com.celfocus.training.entity.cart.ShoppingCartItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Temos 4 entidades em nosso projeto User, ShoppingCart, ShoppingCartItem e ItemInfo
 */
public class Saver {

    private static final List<User> users = new ArrayList<>();
    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static final List<ItemInfo> items = new ArrayList<>();

    public User saveOrUpdateUser(String name, Date birthDate, boolean isOlder) {
        if (this.findUserByName(name)) {
            return this.updateUser(name, birthDate, isOlder);
        } else {
            return this.createUser(name, birthDate, isOlder);
        }
    }

    private User createUser(String name, Date birthDate, boolean isOlder) {
        User user = new User(name, birthDate, isOlder);
        users.add(user);
        ShoppingCart s = new ShoppingCart();
        s.setUser(user);
        s.setItems(new ArrayList<>());
        shoppingCarts.add(s);
        return user;
    }

    private User updateUser(String name, Date birthDate, boolean isOlder) {
        User user = this.getUserByName(name);
        user.setBirthday(birthDate);
        user.setNotMinor(isOlder);
        ShoppingCart shoppingCartFound = this.getShoppingCartByUser(user);

        if (shoppingCartFound == null) {
            ShoppingCart s = new ShoppingCart();
            s.setUser(user);
            shoppingCarts.add(s);
        }
        users.add(user);
        return user;
    }

    public void addItemToUser(String user, String itemName, int quantity) {
        User userFound = this.getUserByName(user);
        if (Objects.isNull(userFound)) {
            return;
        }

        ShoppingCart shoppingCartFound = this.getShoppingCartByUser(userFound);
        if (Objects.isNull(shoppingCartFound)) {
            return;
        }

        ShoppingCartItem cartItemFound = this.getShoppingCartItem(itemName, shoppingCartFound);
        if (Objects.nonNull(cartItemFound)) {
            int quantitySum = cartItemFound.getQuantity() + quantity;
            cartItemFound.setQuantity(quantitySum);
        }
    }

    private ShoppingCart getShoppingCartByUser(User userFound) {
        ShoppingCart shoppingCartFound = null;
        for (ShoppingCart var : shoppingCarts) {
            if (var.getUser() == userFound) {
                shoppingCartFound = var;
            }
        }
        return shoppingCartFound;
    }

    private boolean findUserByName(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.getName().equals(name)) {
                userFound = user;
            }
        }
        return userFound != null;
    }

    private User getUserByName(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.getName().equals(name)) {
                userFound = user;
            }
        }
        return userFound;
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

    private ItemInfo findItemByName(String name) {
        ItemInfo itemFound = null;
        for (ItemInfo item : items) {
            if (item.getName().equals(name)) {
                itemFound = item;
            }
        }
        return itemFound;
    }
} 
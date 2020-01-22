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
            User user = this.getUserByName(name);
            user.setBirthday(birthDate);
            user.setNotMinor(isOlder);
            ShoppingCart shoppingCartFound = null;
            for (ShoppingCart var : shoppingCarts) {
                if (var.getUser() == user) {
                    shoppingCartFound = var;
                }
            }

            if (shoppingCartFound == null) {
                ShoppingCart s = new ShoppingCart();
                s.setUser(user);
                shoppingCarts.add(s);
            }
            users.add(user);
            return user;
        } else {
            User user = new User(name, birthDate, isOlder);
            users.add(user);
            ShoppingCart s = new ShoppingCart();
            s.setUser(user);
            s.setItems(new ArrayList<>());
            shoppingCarts.add(s);
            return user;
        }
    }

    public void increaseItemQuantity(String user, String itemName, int quantity) {
        User userFound = this.getUserByName(user);
        if (Objects.isNull(userFound)) {
            return;
        }

        ShoppingCart shoppingCartFound = null;
        for (ShoppingCart var : shoppingCarts) {
            if (var.getUser() == userFound) {
                shoppingCartFound = var;
            }
        }

        if (Objects.isNull(shoppingCartFound)) {
            return;
        }

        ShoppingCartItem cartItemFound = this.getShoppingCartItem(itemName, shoppingCartFound);

        if (Objects.nonNull(cartItemFound)) {
            int aux = cartItemFound.getQuantity() + quantity;
            cartItemFound.setQuantity(aux);
        } else {
            ItemInfo itemInfo = this.findItemByName(itemName);
            if (Objects.isNull(itemInfo)) {
                return;
            }

            ShoppingCartItem s1 = new ShoppingCartItem();
            s1.setItem(itemInfo);
            s1.setQuantity(quantity);
            if (userFound.isNotMinor() && (new Date().getYear() - userFound.getBirthday().getYear() < 80)) {
                s1.setDiscount(0.2);
            } else if (userFound.isNotMinor()) {
                s1.setDiscount(0.1);
            }
        }
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
package com.celfocus.training;

import com.celfocus.training.entity.ItemInfo;
import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.ShoppingCartItem;
import com.celfocus.training.entity.User;
import com.celfocus.training.util.Utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Temos 4 entidades em nosso projeto User, ShoppingCart, ShoppingCartItem e ItemInfo
 */
public class Saver {

    private static final List<User> users = new ArrayList<>();
    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static final List<ItemInfo> itens = new ArrayList<>();

    public void saveOrUpdateUser(String userName, Date birthDay, boolean isAdult) {
        if (userExists(userName)) {
            updateUser(userName, birthDay, isAdult);
        } else {
            saveUser(userName, birthDay, isAdult);
        }
    }

    private void saveUser(String userName, Date birthDay, boolean isAdult) {
        User user = new User();
        user.birthDay = birthDay;
        user.userName = userName;
        user.isAdult = isAdult;
        users.add(user);
        createShoppingCartForUser(user);
    }

    private void updateUser(String userName, Date birthDay, boolean isAdult) {
        User user = getUserFromName(userName);
        user.birthDay = birthDay;
        user.isAdult = isAdult;
        users.add(user);
        boolean userDoesNottHaveShoppingCart = !userHasShoppingCart(user);
        if (userDoesNottHaveShoppingCart) {
            createShoppingCartForUser(user);
        }
    }

    public void saveItemToShoppingCart(String userName, String itemName, int itemQuantity) {
        User user = getUserFromName(userName);
        if (user == null) return;

        ShoppingCart shoppingCart = getShoppingCartFromUser(user);
        if (shoppingCart == null) {
            createShoppingCartForUser(user);
            shoppingCart = getShoppingCartFromUser(user);
        }

        ShoppingCartItem shoppingCartItem = shoppingCart.getItemInShoppingCartFromName(itemName);

        if (shoppingCartItem != null) {
            shoppingCartItem.quantity += itemQuantity;
        } else {
            ItemInfo item = getItemInfoFromName(itemName);
            if (item == null) return;

            ShoppingCartItem newShoppingCartItem = new ShoppingCartItem();
            newShoppingCartItem.itemInfo = item;
            newShoppingCartItem.quantity = itemQuantity;
            newShoppingCartItem.discount = calculateDiscount(user);
            shoppingCart.shoppingCartItems.add(newShoppingCartItem);
        }
    }

    public static double calculateDiscount(User user) {
        boolean userIsAChild = !user.isAdult;
        if (userIsAChild) return 0.0;

        int userAge = user.getUserAge(user);
        boolean userIsOld = userAge < 80;

        if (userIsOld) {
            return 0.2;
        }
        return 0.1;
    }

    private void createShoppingCartForUser(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.user = user;
        shoppingCart.shoppingCartItems = new ArrayList<>();
        shoppingCarts.add(shoppingCart);
    }

    public void removeItemFromShoppingCart(String userName, String itemName) {
        User user = getUserFromName(userName);
        if (user == null) return;

        ShoppingCart shoppingCart = getShoppingCartFromUser(user);
        if (shoppingCart == null) return;
        shoppingCart.shoppingCartItems.removeIf(shoppingCartItem -> shoppingCartItem.itemInfo.itemName.equals(itemName));
    }

    public void addItemIfNotExists(String itemName, double itemValue) {
        ItemInfo item = getItemInfoFromName(itemName);

        if (item == null) {
            ItemInfo newItem = new ItemInfo();
            newItem.itemName = itemName;
            newItem.value = itemValue;
            itens.add(newItem);
        }
    }



    private boolean userExists(String name) {
        return users.stream().anyMatch(user -> user.userName.equals(name));
    }

    private User getUserFromName(String name) {
        return users.stream().filter(user -> user.userName.equals(name)).findAny().orElse(null);
    }

    private Boolean userHasShoppingCart(User user) {
        return shoppingCarts.stream().anyMatch(shoppingCart -> shoppingCart.user.equals(user));
    }

    private ShoppingCart getShoppingCartFromUser(User user) {
        return shoppingCarts.stream().filter(shoppingCart -> shoppingCart.user.equals(user)).findAny().orElse(null);
    }

    public ItemInfo getItemInfoFromName(String itemName) {
        return itens.stream().filter(item -> item.itemName.equals(itemName)).findAny().orElse(null);
    }

    public void deleteUser(String userName) {
        User user = getUserFromName(userName);
        if (user != null) {
            users.remove(user);
        }
    }

} 
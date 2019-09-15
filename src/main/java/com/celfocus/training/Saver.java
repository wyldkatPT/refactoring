package com.celfocus.training;

import com.celfocus.training.entity.ItemInfo;
import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.ShoppingCartItem;
import com.celfocus.training.entity.User;
import com.celfocus.training.exceptions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Temos 4 entidades em nosso projeto com.celfocus.training.entity.User, com.celfocus.training.entity.ShoppingCart, com.celfocus.training.entity.ShoppingCartItem e ItemInfo
 */
public class Saver {

    private static final List<User> users = new ArrayList<>();
    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static final List<ItemInfo> items = new ArrayList<>();

    public User saveOrUpdateUser(String userName, Date birthDate, boolean ifUserIsOlder) {
        if (userExists(userName)) {
            User user = findUserByName(userName);
            user.birthDate = birthDate;
            user.ifUserIsOlder = ifUserIsOlder;
            ShoppingCart found = findShoppingCartByUser(user);

            if (found != null) {
                //do nothing
            } else {
                ShoppingCart s = new ShoppingCart();
                s.user = user;
                shoppingCarts.add(s);
            }
            users.add(user);
            return user;
        } else {
            User user = new User();
            user.birthDate = birthDate;
            user.name = userName;
            user.ifUserIsOlder = ifUserIsOlder;
            users.add(user);
            ShoppingCart s = new ShoppingCart();
            s.user = user;
            s.items = new ArrayList<>();
            shoppingCarts.add(s);
            return user;
        }
    }

    private boolean userExists(String userName) {
        return findUserByName(userName) != null;
    }


    public void deleteUserOrNot(String userName) {
        User userFound = findUserByName(userName);
        if (userFound != null) {
            users.remove(userFound);
        }
    }

    public void addItemToShoppingCart(String userName, String itemName, int quantity) throws UserNotFoundException, ShoppingCartNotFoundException, ShoppingCartItemNotFoundException, ItemInfoNotFoundException {
        User user = findUserByName(userName);

        if (user == null) {
            throw new UserNotFoundException("User does not exist in our Database");
        }

        ShoppingCart userShoppingCart = findShoppingCartByUser(user);

        if (userShoppingCart == null) {
            throw new ShoppingCartNotFoundException("ShoppingCart does not exist in our Database");
        }

        ShoppingCartItem userShoppingCartItem = findShoppingCartItemByItemName(itemName, userShoppingCart);

        if (userShoppingCartItem == null) {
            throw new ShoppingCartItemNotFoundException("ShoppingCartItem does not exist in our Database");
        }

        userShoppingCartItem.quantity += quantity;

        ItemInfo itemInfo = findItemByName(itemName);

        if (itemInfo == null) {
            throw new ItemInfoNotFoundException("No info found on this item in our Database");
        }

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.item = itemInfo;
        shoppingCartItem.quantity = quantity;
        if (user.ifUserIsOlder && isLessThanEightyYearsOld(user)) {
            shoppingCartItem.discount = 0.2;
        } else if (user.ifUserIsOlder) {
            shoppingCartItem.discount = 0.1;
        }

    }

    private boolean isLessThanEightyYearsOld(User user) {
        return new Date().getYear() - user.birthDate.getYear() < 80;
    }

    public void removeItemFromShoppingCart(String userName, String nameItem) throws UserNotFoundException, ShoppingCartNotFoundException, ShoppingCartItemNotFoundException {
        User userFound = findUserByName(userName);

        if (userFound == null) {
            throw new UserNotFoundException("User does not exist in our Database");
        }

        ShoppingCart found = findShoppingCartByUser(userFound);

        if (found == null) {
            throw new ShoppingCartNotFoundException("ShoppingCart does not exist in our Database");
        }

        ShoppingCartItem scif = findShoppingCartItemByItemName(nameItem, found);

        if (scif == null) {
            throw new ShoppingCartItemNotFoundException("ShoppingCartItem does not exist in our Database");
        }

        found.items.remove(scif);
    }

    public void createItemIfNotExists(String itemName, double itemValue) {
        ItemInfo itemInfo = findItemByName(itemName);
        if (itemInfo == null) {
            itemInfo = new ItemInfo();
            itemInfo.name = itemName;
            itemInfo.value = itemValue;
            items.add(itemInfo);
        }
    }

    private ItemInfo findItemByName(String itemName) {
        ItemInfo itemFound = null;
        for (ItemInfo item : items) {
            if (item.name.equals(itemName)) {
                itemFound = item;
            }
        }
        return itemFound;
    }

    private User findUserByName(String userName) {
        User userFound = null;
        for (User user : users) {
            if (user.name.equals(userName)) {
                userFound = user;
            }
        }
        return userFound;
    }

    private ShoppingCart findShoppingCartByUser(User user) {
        ShoppingCart userShoppingCart = null;
        for (ShoppingCart shoppingCart : shoppingCarts) {
            if (shoppingCart.user == user) {
                userShoppingCart = shoppingCart;
            }
        }
        return userShoppingCart;
    }

    private ShoppingCartItem findShoppingCartItemByItemName(String itemName, ShoppingCart userShoppingCart) {
        ShoppingCartItem userShoppingCartItem = null;
        for (ShoppingCartItem shoppingCartItem : userShoppingCart.items) {
            if (shoppingCartItem.item.name.equals(itemName)) {
                userShoppingCartItem = shoppingCartItem;
            }
        }
        return userShoppingCartItem;
    }
}
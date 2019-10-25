
package com.celfocus.training;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Temos 4 entidades em nosso projeto User, ShoppingCart, ShoppingCartItem e ItemInfo
 */
public class Saver {

    private static final List<User> users = new ArrayList<>();
    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static final List<ItemInfo> items = new ArrayList<>();

    public static class User {
        
        public String userName;
        public Date birthdayDate;
        public boolean isAdult;

    }

    public static class ShoppingCart {
        
        public User user;
        public List<ShoppingCartItem> items;
    }

    public static class ShoppingCartItem {

        public ItemInfo item;
        public int quantity;
        public double discount;

    }

    public static class ItemInfo {

        public String name;
        public double value;

    }

    public User createOrUpdateUser(String name, Date birthdayDate, boolean isAdult) {

        User user = UserExists(name) ? updateUser(name, birthdayDate, isAdult) : createUser(name, birthdayDate, isAdult);

        return user;

    }

    private User updateUser(String name, Date birthdayDate, boolean isAdult) {

        User user = getUser(name);
        setUserDetails(name, birthdayDate, isAdult, user);
        users.add(user);

        boolean hasShoppingCart = getUserShoppingCart(user) != null;

        if (!hasShoppingCart){
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.user = user;
            shoppingCarts.add(shoppingCart);
        }

        return user;
    }

    private ShoppingCart getUserShoppingCart(User user) {

        ShoppingCart userShoppingCart = null;

        for (ShoppingCart shoppingCart : shoppingCarts) {
            if (shoppingCart.user == user) {
                userShoppingCart = shoppingCart;
                break;
            }
        }
        return userShoppingCart;
    }

    private User createUser(String name, Date birthdayDate, boolean isAdult) {

        User user = new User();
        setUserDetails(name, birthdayDate, isAdult, user);
        users.add(user);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.user = user;
        shoppingCart.items = new ArrayList<>();
        shoppingCarts.add(shoppingCart);

        return user;
    }

    private void setUserDetails(String name, Date birthdayDate, boolean isAdult, User user) {
        user.birthdayDate = birthdayDate;
        user.userName = name;
        user.isAdult = isAdult;
    }

    private boolean UserExists(String name) {
        User userFound = getUser(name);
        return userFound != null;
    }

    private User getUser(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.userName.equals(name)) {
                userFound = user;
                break;
            }
        }
        return userFound;
    }


    public void deleteUser(String name) {

        User userFound = getUser(name);
        if (userFound != null) {
            users.remove(userFound);
        }
    }

    public void addItemToUser(String name, String itemName, int quantity) {

        User user = getUser(name);
        
        if (user != null) {
            ShoppingCart ShoppingCart = getUserShoppingCart(user);

            if (ShoppingCart != null) {
                ShoppingCartItem shoppingCartItem = getShoppingCartItem(itemName, ShoppingCart);
                updateShoppingCart(itemName, quantity, user, shoppingCartItem);
            }
        }
    }

    private void updateShoppingCart(String itemName, int quantity, User user, ShoppingCartItem shoppingCartItem) {

        if (shoppingCartItem != null) {
            updateShoppingCartItemQuantity(quantity, shoppingCartItem);
        } else {
            updateShoppingCartItem(itemName, quantity, user);
        }
    }

    private void updateShoppingCartItem(String itemName, int quantity, User user) {
        ItemInfo itemInfo = getItemInfo(itemName);

        if (itemInfo != null) {
            ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
            shoppingCartItem.item = itemInfo;
            shoppingCartItem.quantity = quantity;

            validateUserDiscount(user, shoppingCartItem);
        }
    }

    private ItemInfo getItemInfo(String itemName) {
        ItemInfo itemInfo = null;
        for (ItemInfo item : items) {
            if (item.name.equals(itemName)) {
                itemInfo = item;
            }
        }
        return itemInfo;
    }

    private void validateUserDiscount(User user, ShoppingCartItem shoppingCartItem1) {
        if (user.isAdult){
            shoppingCartItem1.discount = 0.1;
            if ((getUserAge(user) < 80)){
                shoppingCartItem1.discount = 0.2;
            }
        }
    }

    private int getUserAge(User user) {
        return new Date().getYear() - user.birthdayDate.getYear();
    }

    private void updateShoppingCartItemQuantity(int quantity, ShoppingCartItem shoppingCartInfo) {
        shoppingCartInfo.quantity += quantity;
    }

    private ShoppingCartItem getShoppingCartItem(String nameItem, ShoppingCart shoppingCart) {
        ShoppingCartItem shoppingCartItemData = null;
        for (ShoppingCartItem shoppingCartItem : shoppingCart.items) {
            if (shoppingCartItem.item.name == nameItem) {
                shoppingCartItemData = shoppingCartItem;
            }
        }
        return shoppingCartItemData;
    }
} 
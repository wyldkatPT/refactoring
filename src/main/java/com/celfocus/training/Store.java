package com.celfocus.training;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Store {

    private static final ArrayList<User> userList = new ArrayList<>();
    private static final ArrayList<ShoppingCart> shoppingCartList = new ArrayList<>();
    private static final ArrayList<ItemInfo> itemList = new ArrayList<>();

    public static ArrayList<User> getUserList() {
        return userList;
    }

    public static ArrayList<ShoppingCart> getShoppingCartList() {
        return shoppingCartList;
    }


    public User saveOrUpdateUser(String name, Date birthdayDate, boolean isAgeOver18) {
        if (userFound(name)) {
            return updateUser(name,birthdayDate,isAgeOver18);
        } else {
            return saveUser(name,birthdayDate,isAgeOver18);
        }
    }

    public User updateUser(String name, Date birthdayDate, boolean isAgeOver18) {
        User user = getUser(name);
        user.setBirthdayDate(birthdayDate);
        user.setIsAgeOver18(isAgeOver18);
        ShoppingCart cart = getShoppingCart(user);

        if (!shoppingCartFound(user)) {
            ShoppingCart shoppingCart = new ShoppingCart(user);
            shoppingCartList.add(shoppingCart);
        }
        userList.add(user);
        return user;
    }

    public User saveUser(String name, Date birthdayDate, boolean isAgeOver18) {
        User user = new User(name,birthdayDate,isAgeOver18);
        userList.add(user);
        ArrayList<ShoppingCartItem> items = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart(user,items);
        shoppingCartList.add(shoppingCart);
        return user;
    }

    public void deleteUser(String name) {
        if (userFound(name)) {
            userList.remove(getUser(name));
        }
    }


    private boolean userFound(String name) {
        for (User user : userList) {
            if (user.getNameOfUser().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private User getUser(String name){
        for (User user : userList){
            if (user.getNameOfUser().equals(name)){
                return user;
            }
        }
        return null;
    }

    //Shopping Car Validation
    private boolean shoppingCartFound(User user) {
        for (ShoppingCart shoppingCart : shoppingCartList) {
            if (shoppingCart.getUser().equals(user)) {
                return true;
            }
        }
        return false;
    }

    private ShoppingCart getShoppingCart(User user){
        for (ShoppingCart shoppingCart : shoppingCartList) {
            if (shoppingCart.getUser().equals(user)) {
                return shoppingCart;
            }
        }
        return null;
    }

    private ItemInfo getItem(String name) {
        for (ItemInfo item : itemList) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

}

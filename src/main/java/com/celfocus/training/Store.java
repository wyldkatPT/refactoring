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


    public void addItemUser(String userName, String nameItem, int quantity) {
        if (userFound(userName)) {
            User user = getUser(userName);
            if (shoppingCartFound(user)) {
                if (shoppingCartItemFound(user, nameItem)) {
                    ShoppingCartItem shoppingCartItem = getShoppingCartItem(user, nameItem);
                    shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() + quantity);
                } else {
                    if (!itemFound(nameItem)) {
                        addItem(nameItem, 0.0);
                    }
                    ItemInfo item = getItem(nameItem);
                    ShoppingCartItem shoppingCartItem = new ShoppingCartItem(item, quantity, getDiscount(user));

                }
            }
        }
    }

    public void removeItemUser(String userName, String nameItem) {
        if (userFound(userName)) {
            User user = getUser(userName);
            if (shoppingCartFound(user)) {
                if (shoppingCartItemFound(user, nameItem)) {
                    ShoppingCartItem shoppingCartItem = getShoppingCartItem(user, nameItem);
                    ShoppingCart shoppingCart = getShoppingCart(user);
                    ArrayList <ShoppingCartItem> cartItemList = shoppingCart.getItems();
                    cartItemList.remove(shoppingCartItem);
                    shoppingCart.setItems(cartItemList);
                } else {
                }
            }
        }
    }


    public User saveOrUpdateUser(String name, Date birthdayDate) {
        if (userFound(name)) {
            return updateUser(name,birthdayDate);
        } else {
            return saveUser(name,birthdayDate);
        }
    }

    public User updateUser(String name, Date birthdayDate) {
        User user = getUser(name);
        user.setBirthdayDate(birthdayDate);
        ShoppingCart cart = getShoppingCart(user);

        if (!shoppingCartFound(user)) {
            ShoppingCart shoppingCart = new ShoppingCart(user);
            shoppingCartList.add(shoppingCart);
        }
        userList.add(user);
        return user;
    }

    public User saveUser(String name, Date birthdayDate) {
        User user = new User(name,birthdayDate);
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

    private boolean shoppingCartItemFound(User user, String itemName){
        ShoppingCart shoppingCart = getShoppingCart(user);
        for (ShoppingCartItem shoppingCartItem : shoppingCart.getItems()) {
            if (shoppingCartItem.getItem().getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    private ShoppingCartItem getShoppingCartItem(User user, String itemName){
        ShoppingCart shoppingCart = getShoppingCart(user);
        for (ShoppingCartItem shoppingCartItem : shoppingCart.getItems()) {
            if (shoppingCartItem.getItem().getName().equals(itemName)) {
                return shoppingCartItem;
            }
        }
        return null;
    }

    private boolean itemFound(String itemName){
        for (ItemInfo item : itemList) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    private void addItem (String itemName, double value){
        ItemInfo item = new ItemInfo(itemName,value);
        itemList.add(item);
    }


    private ItemInfo getItem(String itemName) {
        for (ItemInfo item : itemList) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    private double getDiscount(User user){
        int age = user.getAge();
        if (age > 80) return 0.2;
        else if (age > 18) {
            return 0.1;
        }
        else return 0.0;
     }

}

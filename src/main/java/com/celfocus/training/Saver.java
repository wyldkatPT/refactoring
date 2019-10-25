package com.celfocus.training;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Temos 4 entidades em nosso projeto User, ShoppingCart, ShoppingCartItem e ItemInfo
 */
public class Saver {
    private static final List<User> users = new ArrayList<>();
    private static final List<ShoppingCart> shoppingCart = new ArrayList<>();
    private static final List<ItemInfo> items = new ArrayList<>();

    public User saveOrUpdateUser(String name, Date birthDate, boolean isAdult) {
        if (wasUserFound(name)) {
            User user = userFound(name);
            user.setBirthDate(birthDate);
            user.isAdult = isAdult;
            ShoppingCart found = null;
            for (ShoppingCart var : shoppingCart) {
                if (var.user == user) {
                    found = var;
                }
            }
            if (found == null) {
                ShoppingCart s = new ShoppingCart();
                s.user = user;
                shoppingCart.add(s);
            }
            users.add(user);
            return user;
        } else {
            User user = new User();
            user.setBirthDate(birthDate);
            user.setUserName(name);
            user.isAdult = isAdult;
            users.add(user);
            ShoppingCart s = new ShoppingCart();
            s.user = user;
            s.items = new ArrayList<>();
            shoppingCart.add(s);
            return user;
        }
    }

    private boolean wasUserFound(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.getUserName().equals(name)) {
                userFound = user;
            }
        }
        return userFound != null;
    }

    private User userFound(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.getUserName().equals(name)) {
                userFound = user;
            }
        }
        return userFound;
    }

    public ItemInfo itemFound(String name) {
        ItemInfo itemFound = null;
        for (ItemInfo item : items) {
            if (item.name.equals(name)) {
                itemFound = item;
            }
        }
        return itemFound;
    }

    public void deleteUserOrNot(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.getUserName().equals(name)) {
                userFound = user;
            }
        }
        if (userFound != null) {
            users.remove(userFound);
        }
    }

    public void addItemUser(String user, String nameItem, int quantity) {
        User userFound = null;
        for (User userName : users)
            if (userName.getUserName().equals(user)) {
                userFound = userName;
            }

        if (userFound != null) {
            ShoppingCart shoppingCartFound = null;
            for (ShoppingCart var : shoppingCart) {
                if (var.user == userFound) {
                    shoppingCartFound = var;
                }
            }

            if (shoppingCartFound != null) {
                ShoppingCartItem shoppingCartItemFound = null;
                for (ShoppingCartItem shoppingCartItem : shoppingCartFound.items) {
                    if (shoppingCartItem.item.name == nameItem) {
                        shoppingCartItemFound = shoppingCartItem;
                    }
                }

                if (shoppingCartItemFound != null) {
                    shoppingCartItemFound.quantity += quantity;
                } else {
                    ItemInfo ifo = null;
                    for (ItemInfo item : items) {
                        if (item.name.equals(nameItem)) {
                            ifo = item;
                        }
                    }

                    if (ifo != null) {
                        ShoppingCartItem s1 = new ShoppingCartItem();
                        s1.item = ifo;
                        s1.quantity = quantity;
                        if (userFound.isAdult && (new Date().getYear() - userFound.getBirthDate().getYear() < 80) ) {
                            s1.discount = 0.2;
                        } else if (userFound.isAdult) {
                            s1.discount = 0.1;
                        }
                    }
                }
            }
        }
    }

    public void removeItemUser(String user, String nameItem) {
        User userFound = null;
        for (User user1 : users) {
            if (user1.getUserName().equals(user)) {
                userFound = user1;
            }
        }

        if (userFound != null) {
            ShoppingCart found = null;
            for (ShoppingCart var : shoppingCart) {
                if (var.user == userFound) {
                    found = var;
                }
            }

            if (found != null) {
                ShoppingCartItem shoppingCartItemFound = null;
                for (ShoppingCartItem shoppingCartItem : found.items) {
                    if (shoppingCartItem.item.name.equals(nameItem)) {
                        shoppingCartItemFound = shoppingCartItem;
                    }
                }

                if (shoppingCartItemFound != null) {
                    found.items.remove(shoppingCartItemFound);
                }
            }
        }
    }

    public void itemValidation(String itemName, double v) {
        ItemInfo item = null;
        for (ItemInfo i : items){
            if (i.name.equals(itemName)) item = i;
        }

        if (item == null) {
            ItemInfo ift = new ItemInfo();
            ift.name = itemName;
            ift.value = v;
            items.add(ift);
        }
    }
}
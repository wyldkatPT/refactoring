package com.celfocus.training;

import com.celfocus.training.user.User;
import com.celfocus.training.item.ItemInfo;

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

    public static class ShoppingCart {
        public User user;
        public List<ShoppingCartItem> items;
    }

    public static class ShoppingCartItem {
        public ItemInfo item;
        public int quantity;
        public double discount;
    }

    public User saveOrUpdateUser(String name, Date birthDate, boolean isAdult) {
        if (wasUserFound(name)) {
            User user = userFound(name);
            user.setBirthDate(birthDate);
            user.isAdult = isAdult;
            ShoppingCart found = null;
            for (ShoppingCart var : shoppingCarts) {
                if (var.user == user) {
                    found = var;
                }
            }
            if (found == null) {
                ShoppingCart s = new ShoppingCart();
                s.user = user;
                shoppingCarts.add(s);
            }
            users.add(user);
            return user;
        } else {
            User user = new User();
            user.setBirthDate(birthDate);
            user.setUsername(name);
            user.isAdult = isAdult;
            users.add(user);
            ShoppingCart s = new ShoppingCart();
            s.user = user;
            s.items = new ArrayList<>();
            shoppingCarts.add(s);
            return user;
        }
    }

    private boolean wasUserFound(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                userFound = user;
            }
        }
        return userFound != null;
    }

    private User userFound(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                userFound = user;
            }
        }
        return userFound;
    }

    public ItemInfo encontrarItem(String name) {
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
            if (user.getUsername().equals(name)) {
                userFound = user;
            }
        }
        if (userFound == null) {
        } else {
            users.remove(userFound);
        }
    }

    public void aIU(String user, String nameItem, int qt) {
        User userFound = null;
        for (User user1 : users) {
            if (user1.getUsername().equals(user)) {
                userFound = user1;
            }
        }

        if (userFound != null) {
            ShoppingCart found = null;
            for (ShoppingCart var : shoppingCarts) {
                if (var.user == userFound) {
                    found = var;
                }
            }

            if (found != null) {
                ShoppingCartItem scif = null;
                for (ShoppingCartItem s : found.items) {
                    if (s.item.name == nameItem) {
                        scif = s;
                    }
                }

                if (scif != null) {
                    scif.quantity += qt;
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
                        s1.quantity = qt;
                        if ( userFound.isAdult == true && (new Date().getYear() - userFound.getBirthDate().getYear() < 80) ) {
                            s1.discount = 0.2; 
                        } else if (userFound.isAdult == true) {
                            s1.discount = 0.1;
                        }
                    } else {

                    }
                    
                }
            }
        }
    }

    public void rIU(String user, String nameItem) {
        User userFound = null;
        for (User user1 : users) {
            if (user1.getUsername().equals(user)) {
                userFound = user1;
            }
        }

        if (userFound != null) {
                ShoppingCart found = null;
                for (ShoppingCart var : shoppingCarts) {
                    if (var.user == userFound) {
                        found = var;
                    }
                }

            if (found != null) {
                ShoppingCartItem scif = null;
                for (ShoppingCartItem s : found.items) {
                    if (s.item.name == nameItem) {
                        scif = s;
                    }
                }

                if (scif != null) {
                    found.items.remove(scif);
                }
            }
        }
    }

    public void citemifnotexists(String arg0, double v) {
        ItemInfo f = null;
        for (ItemInfo i : items){
            if (i.name == arg0) {
                f = i;
            }
        }

        if ( f != null ) {

        } else {
            ItemInfo ift = new ItemInfo();
            ift.name = arg0;
            ift.valor = v;
            items.add(ift);
        }
    }

} 
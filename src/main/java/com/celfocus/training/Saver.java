package com.celfocus.training;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Temos 4 entidades em nosso projeto User, ShoppingCart, ShoppingCartItem e ItemInfo
 */
public class Saver {

//    private static final List<User> usersList = new ArrayList<>();
//    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
//    private static final List<ItemInfo> itensList = new ArrayList<>();


    public User saveOrUpdateUser(String name, Date birthdayDate, boolean isOlder) {
        if (isUserFound(name)) {
            User User = fu(name);
            User.userBirthday = birthdayDate;
            User.isUserOlder = isOlder;
            ShoppingCart found = null;
            for (ShoppingCart var : shoppingCarts) {
                if (var.User == User) {
                    found = var;
                }
            }

            if (found != null) {
                //do nothing
            } else {
                ShoppingCart s = new ShoppingCart();
                s.User = User;
                shoppingCarts.add(s);
            }
            usersList.add(User);
            return User;
        } else {
            User User = new User();
            User.userBirthday = birthdayDate;
            User.userName = name;
            User.isUserOlder = isOlder;
            usersList.add(User);
            ShoppingCart s = new ShoppingCart();
            s.User = User;
            s.itens = new ArrayList<>();
            shoppingCarts.add(s);
            return User;
        }
    }


//    public void aIU(String user, String nameItem, int itemQuantity) {
//        User userFound = null;
//        for (User user1 : usersList) {
//            if (user1.userName.equals(user)) {
//                userFound = user1;
//            }
//        }
//
//        if (userFound != null) {
//            ShoppingCart found = null;
//            for (ShoppingCart var : shoppingCarts) {
//                if (var.user == userFound) {
//                    found = var;
//                }
//            }
//
//            if (found != null) {
//                ShoppingCartItem scif = null;
//                for (ShoppingCartItem s : found.itens) {
//                    if (s.itemInfo.itemName == nameItem) {
//                        scif = s;
//                    }
//                }
//
//                if (scif != null) {
//                    scif.itemQuantity += itemQuantity;
//                } else {
//                    ItemInfo ifo = null;
//                    for (ItemInfo item : itensList) {
//                        if (item.itemName.equals(nameItem)) {
//                            ifo = item;
//                        }
//                    }
//
//                    if (ifo != null) {
//                        ShoppingCartItem s1 = new ShoppingCartItem();
//                        s1.itemInfo = ifo;
//                        s1.itemQuantity = itemQuantity;
//                        if ( userFound.isUserOlder
//                 == true && (new Date().getYear() - userFound.userBirthday.getYear() < 80) ) {
//                            s1.discount = 0.2;
//                        } else if (userFound.isUserOlder
//                 == true) {
//                            s1.discount = 0.1;
//                        }
//                    } else {
//
//                    }
//
//                }
//            }
//        }
//    }

    public void rIU(String user, String nameItem) {
        User userFound = null;
        for (User user1 : usersList) {
            if (user1.userName.equals(user)) {
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
                for (ShoppingCartItem s : found.itens) {
                    if (s.itemInfo.itemName == nameItem) {
                        scif = s;
                    }
                }

                if (scif != null) {
                    found.itens.remove(scif);
                }
            }
        }
    }

} 
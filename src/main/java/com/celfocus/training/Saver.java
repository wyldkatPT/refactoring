package com.celfocus.training;

import com.celfocus.training.util.ItemInfo;
import com.celfocus.training.util.ShoppingCart;
import com.celfocus.training.util.ShoppingCartItem;
import com.celfocus.training.util.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Temos 4 entidades em nosso projeto User, ShoppingCart, ShoppingCartItem e ItemInfo
 */
public class Saver {

    private static final List<User> users = new ArrayList<>();
    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static final List<ItemInfo> itens = new ArrayList<>();

    public User saveOrUpdateUser(String nameOfUser, Date dateOfBirth, boolean isLegalAge) {
        if (isFoundUser(nameOfUser)) {
            User user = findUser(nameOfUser);
            user.dateOfBirth = dateOfBirth;
            user.isLegalAge = isLegalAge;
            ShoppingCart found = null;
            for (ShoppingCart var : shoppingCarts) {
                if (var.user == user) {
                    found = var;
                }
            }

            if (found == null) addShoppingCart(user);
            users.add(user);
            return user;
        } else {
            User user = new User();
            user.dateOfBirth = dateOfBirth;
            user.nameOfUser = nameOfUser;
            user.isLegalAge = isLegalAge;
            users.add(user);
            addShoppingCart(user);
            return user;
        }
    }

    private void addShoppingCart(User user) {
        ShoppingCart s = new ShoppingCart();
        s.user = user;
        s.itens = new ArrayList<>();
        shoppingCarts.add(s);
    }

    private boolean isFoundUser(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.nameOfUser.equals(name)) {
                userFound = user;
            }
        }
        return userFound != null;
    }

    private User findUser(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.nameOfUser.equals(name)) {
                userFound = user;
            }
        }
        return userFound;
    }

    public ItemInfo encontrarItem(String name) {
        ItemInfo itemFound = null;
        for (ItemInfo item : itens) {
            if (item.name.equals(name)) {
                itemFound = item;
            }
        }
        return itemFound;
    }

    public void deleteUserOrNot(String name) {
//        User userFound = null;
//        for (User user : users) {
//            if (user.nameOfUser.equals(name)) {
//                userFound = user;
//            }
//        }
//        if (userFound == null) {
//        } else {
//            users.remove(userFound);
//        }
        User userFound = findUser(name);
        if (userFound == null) {
        } else {
            users.remove(userFound);
        }
        ;
    }

    public void aIU(String user, String nameItem, int quantity) {
//        User userFound = null;
//        for (User user1 : users) {
//            if (user1.nameOfUser.equals(user)) {
//                userFound = user1;
//            }
//        }
        User userFound = null;
        userFound = findUser(user);

        if (userFound != null) {
            ShoppingCart shoppingCart = null;
            for (ShoppingCart var : shoppingCarts) {
                if (var.user == userFound) {
                    shoppingCart = var;
                }
            }

            if (shoppingCart != null) {
                ShoppingCartItem shoppingCardItemFound = null;
                for (ShoppingCartItem s : shoppingCart.itens) {
                    if (s.item.name == nameItem) {
                        shoppingCardItemFound = s;
                    }
                }

                if (shoppingCardItemFound != null) {
                    shoppingCardItemFound.quantity += quantity;
                } else {
//                    ItemInfo itemInfo = null;
//                    for (ItemInfo item : itens) {
//                        if (item.name.equals(nameItem)) {
//                            itemInfo = item;
//                        }
//                    }
                    ItemInfo itemInfo = encontrarItem(nameItem);

                    if (itemInfo != null) {
                        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
                        shoppingCartItem.item = itemInfo;
                        shoppingCartItem.quantity = quantity;
//                        if ( userFound.isLegalAge == true && (new Date().getYear() - userFound.dateOfBirth.getYear() < 80) ) {
//                            shoppingCartItem.discount = 0.2;
//                        } else if (userFound.isLegalAge == true) {
//                            shoppingCartItem.discount = 0.1;
//                        }
                        shoppingCartItem.discount = (true && (new Date().getYear() - userFound.dateOfBirth.getYear() < 80) ) ? 0.2 : 0.1;
                    }
                }
            }
        }
    }

    public void rIU(String user, String nameItem) {
        User userFound = null;
        for (User user1 : users) {
            if (user1.nameOfUser.equals(user)) {
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
                    if (s.item.name == nameItem) {
                        scif = s;
                    }
                }

                if (scif != null) {
                    found.itens.remove(scif);
                }
            }
        }
    }

//    public void citemifnotexists(String arg0, double v) {
//        ItemInfo f = null;
//        for (ItemInfo i : itens){
//            if (i.name == arg0) {
//                f = i;
//            }
//        }
//
//        if ( f != null ) {
//
//        } else {
//            ItemInfo ift = new ItemInfo();
//            ift.name = arg0;
//            ift.valor = v;
//            itens.add(ift);
//        }
//    }

} 
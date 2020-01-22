package com.celfocus.training;

import com.celfocus.training.entity.User;
import com.celfocus.training.entity.cart.ItemInfo;
import com.celfocus.training.entity.cart.ShoppingCart;
import com.celfocus.training.entity.cart.ShoppingCartItem;

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

    public User saveOrUpdateUser(String name, Date bd, boolean ifuserisolder) {
        if (eu(name)) {
            User user = fu(name);
            user.setBirthday(bd);
            user.setNotMinor(ifuserisolder);
            ShoppingCart found = null;
            for (ShoppingCart var : shoppingCarts) {
                if (var.getUser() == user) {
                    found = var;
                }
            }

            if (found != null) {
                //do nothing
            } else {
                ShoppingCart s = new ShoppingCart();
                s.setUser(user);
                shoppingCarts.add(s);
            }
            users.add(user);
            return user;
        } else {
            User user = new User(name, bd, ifuserisolder);
            users.add(user);
            ShoppingCart s = new ShoppingCart();
            s.setUser(user);
            s.setItems(new ArrayList<>());
            shoppingCarts.add(s);
            return user;
        }
    }

    private boolean eu(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.getName().equals(name)) {
                userFound = user;
            }
        }
        return userFound != null;
    }

    private User fu(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.getName().equals(name)) {
                userFound = user;
            }
        }
        return userFound;
    }

    public ItemInfo encontrarItem(String name) {
        ItemInfo itemFound = null;
        for (ItemInfo item : items) {
            if (item.getName().equals(name)) {
                itemFound = item;
            }
        }
        return itemFound;
    }

    public void deleteUserOrNot(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.getName().equals(name)) {
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
            if (user1.getName().equals(user)) {
                userFound = user1;
            }
        }

        if (userFound != null) {
            ShoppingCart found = null;
            for (ShoppingCart var : shoppingCarts) {
                if (var.getUser() == userFound) {
                    found = var;
                }
            }

            if (found != null) {
                ShoppingCartItem scif = null;
                for (ShoppingCartItem s : found.getItems()) {
                    if (s.getItem().getName() == nameItem) {
                        scif = s;
                    }
                }

                if (scif != null) {
                    int aux = scif.getQuantity() + qt;
                    scif.setQuantity(aux);
                } else {
                    ItemInfo ifo = null;
                    for (ItemInfo item : items) {
                        if (item.getName().equals(nameItem)) {
                            ifo = item;
                        }
                    }

                    if (ifo != null) {
                        ShoppingCartItem s1 = new ShoppingCartItem();
                        s1.setItem(ifo);
                        s1.setQuantity(qt);
                        if (userFound.isNotMinor()
                            == true && (new Date().getYear() - userFound.getBirthday().getYear() < 80)) {
                            s1.setDiscount(0.2);
                        } else if (userFound.isNotMinor()
                            == true) {
                            s1.setDiscount(0.1);
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
            if (user1.getName().equals(user)) {
                userFound = user1;
            }
        }

        if (userFound != null) {
            ShoppingCart found = null;
            for (ShoppingCart var : shoppingCarts) {
                if (var.getUser() == userFound) {
                    found = var;
                }
            }

            if (found != null) {
                ShoppingCartItem scif = null;
                for (ShoppingCartItem s : found.getItems()) {
                    if (s.getItem().getName() == nameItem) {
                        scif = s;
                    }
                }

                if (scif != null) {
                    found.getItems().remove(scif);
                }
            }
        }
    }

    public void citemifnotexists(String arg0, double v) {
        ItemInfo f = null;
        for (ItemInfo i : items) {
            if (i.getName() == arg0) {
                f = i;
            }
        }

        if (f != null) {

        } else {
            ItemInfo ift = new ItemInfo();
            ift.setName(arg0);
            ift.setValor(v);
            items.add(ift);
        }
    }

} 
package com.celfocus.training.controllers;

import com.celfocus.training.models.Product;
import com.celfocus.training.models.ShoppingCart;
import com.celfocus.training.models.ShoppingCartItem;
import com.celfocus.training.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Temos 4 entidades em nosso projeto User, ShoppingCart, ShoppingCartItem e ItemInfo
 */
public class Controller {

        private static final List<User> users = new ArrayList<>();
        private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
        private static final List<Product> products = new ArrayList<>();

        public User saveOrUpdateUser(String name, Date birthday, boolean hasMoreThan18) {
            if (eu(name)) {
                User user = fu(name);
                user.bd = bd;
                user.ifuserisolder = ifuserisolder;
                ShoppingCart found = null;
                for (ShoppingCart var : shoppingCarts) {
                    if (var.user == user) {
                        found = var;
                    }
                }

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
                User user = new User(name, birthday, hasMoreThan18);

                users.add(user);
                ShoppingCart s = new ShoppingCart();
                s.user = user;
                s.itens = new ArrayList<>();
                shoppingCarts.add(s);
                return user;
            }
        }

/*    private boolean eu(String name) {
        User userFound = null;
        for (User views : users) {
            if (views.nameOfUser.equals(name)) {
                userFound = views;
            }
        }
        return userFound != null;
    }*/

/*    private User fu(String name) {
        User userFound = null;
        for (User views : users) {
            if (views.nameOfUser.equals(name)) {
                userFound = views;
            }
        }
        return userFound;
    }*/

        public ItemInfo encontrarItem(String name) {
            ItemInfo itemFound = null;
            for (ItemInfo item : itens) {
                if (item.name.equals(name)) {
                    itemFound = item;
                }
            }
            return itemFound;
        }

    /*public void deleteUserOrNot(String name) {
        User userFound = null;
        for (User views : users) {
            if (views.nameOfUser.equals(name)) {
                userFound = views;
            }
        }
        if (userFound == null) {
        } else {
            users.remove(userFound);
        }
    }*/

        public void aIU(String user, String nameItem, int qt) {
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
                        scif.qt += qt;
                    } else {
                        ItemInfo ifo = null;
                        for (ItemInfo item : itens) {
                            if (item.name.equals(nameItem)) {
                                ifo = item;
                            }
                        }

                        if (ifo != null) {
                            ShoppingCartItem s1 = new ShoppingCartItem();
                            s1.item = ifo;
                            s1.qt = qt;
                            if ( userFound.ifuserisolder
                                    == true && (new Date().getYear() - userFound.bd.getYear() < 80) ) {
                                s1.discount = 0.2;
                            } else if (userFound.ifuserisolder
                                    == true) {
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

        public void citemifnotexists(String arg0, double v) {
            Product f = null;
            for (Product i : itens){
                if (i.name == arg0) {
                    f = i;
                }
            }

            if ( f != null ) {

            } else {
                Product ift = new Product(arg0, v);
                itens.add(ift);
            }
        }

    }}

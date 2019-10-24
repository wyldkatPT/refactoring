package com.celfocus.training.impl;

import com.celfocus.training.models.ShoppingCart;
import com.celfocus.training.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCartImpl {

    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private UserImpl userImpl;
    private ShoppingCartItemImpl shoppingCartItemImpl;

    public ShoppingCartImpl(UserImpl userImpl, ShoppingCartItemImpl shoppingCartItemImpl) {
        this.userImpl = userImpl;
        this.shoppingCartItemImpl = shoppingCartItemImpl;
    }

    public void aIU(String user, String nameItem, int itemQuantity) { //add item to Shopping
        User userFound = null;
        for (User user1 : usersList) {
            if (user1.getName().equals(user)) {
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
                    scif.itemQuantity += itemQuantity;
                } else {
                    ItemInfo ifo = null;
                    for (ItemInfo item : itensList) {
                        if (item.itemName.equals(nameItem)) {
                            ifo = item;
                        }
                    }

                    if (ifo != null) {
                        ShoppingCartItem s1 = new ShoppingCartItem();
                        s1.itemInfo = ifo;
                        s1.itemQuantity = itemQuantity;
                        if ( userFound.isUserOlder
                                == true && (new Date().getYear() - userFound.userBirthday.getYear() < 80) ) {
                            s1.discount = 0.2;
                        } else if (userFound.isUserOlder
                                == true) {
                            s1.discount = 0.1;
                        }
                    } else {

                    }

                }
            }
        }
    }
}

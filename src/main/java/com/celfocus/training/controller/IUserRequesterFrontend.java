package com.celfocus.training.controller;

import com.celfocus.training.entity.User;
import com.celfocus.training.entity.cart.ItemInfo;
import com.celfocus.training.entity.cart.ShoppingCart;

public interface IUserRequesterFrontend {
    String returnFrontendUser(String type, User user);

    String returnFrontendShoppingCart(String type, ShoppingCart shoppingCart);

    String returnFrontendItem(String type, ItemInfo item);

    void createOrUpdateUser(String name, String birthDate, String isUserOlder);

    void deleteUser(String arg0);

    void addItemToShoppingCart(String user, String nameItem, int qt);
}

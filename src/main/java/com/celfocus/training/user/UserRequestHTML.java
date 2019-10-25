package com.celfocus.training.user;

import com.celfocus.training.item.ItemInfo;
import com.celfocus.training.shoppingcart.ShoppingCart;

public class UserRequestHTML implements UserRequestStrategy {
    @Override
    public String returnFrontendUser(User user) {
        return "<div>"
                + "<h1>User</h1>"
                + "<span>" + user.name + "</span>"
                + "<span>" + user.birthDate + "</span>"
                + "<span>" + user.isOlder + "</span>"
                + "</div>";
    }

    @Override
    public String returnFrontendShoppingCart(ShoppingCart shoppingCart) {
        return "<div>"
                + "<h1>ShoppingCart</h1>"
                + "<span> " + shoppingCart.user + "</span>"
                + "<span> " + shoppingCart.itens + "</span>"
                + "</div>";
    }

    @Override
    public String returnFrontendItem(ItemInfo item) {
        return "<div>"
                + "<h1>Item</h1>"
                + "<span> " + item.name + "</span>"
                + "<span> " + item.value + "</span>"
                + "</div>";
    }
}

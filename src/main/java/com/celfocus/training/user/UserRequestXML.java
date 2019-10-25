package com.celfocus.training.user;

import com.celfocus.training.item.ItemInfo;
import com.celfocus.training.shoppingcart.ShoppingCart;

public class UserRequestXML implements UserRequestStrategy {
    @Override
    public String returnFrontendUser(User user) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                + "<name> " + user.name + "</name>"
                + "<birthDate>" + user.birthDate + "</birthDate>"
                + "<older> " + user.isOlder + "</older>";
    }

    @Override
    public String returnFrontendShoppingCart(ShoppingCart shoppingCart) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                + "<user> " + shoppingCart.user + "</user>"
                + "<itens> " + shoppingCart.itens + "</itens>";
    }

    @Override
    public String returnFrontendItem(ItemInfo item) {
        return "<name> " + item.name + "</name>"
                + "<value> " + item.value + "</value>";
    }
}

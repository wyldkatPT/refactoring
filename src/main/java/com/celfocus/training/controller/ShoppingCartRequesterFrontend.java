package com.celfocus.training.controller;

import com.celfocus.training.model.ShoppingCart;
import com.celfocus.training.service.ShoppingCartService;

public class ShoppingCartRequesterFrontend {

    private final ShoppingCartService shoppingCartService;


    public ShoppingCartRequesterFrontend(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    public void addItemToUserShoppingCart(String user, String itemName, int quantity) {
        itemName = itemName.toLowerCase().concat("_item");

        shoppingCartService.addItemToShoppingCart(user, itemName, quantity);
    }

    public String returnFrontendShoppingCart(String type, ShoppingCart shoppingCart) {
        if (type.equals("html")) {
            return "<div>"
                    + "<h1>ShoppingCart</h1>"
                    + "<span> " + shoppingCart.getUser() + "</span>"
                    + "<span> " + shoppingCart.getItems() + "</span>"
                    + "</div>";
        }

        if (type.equals("xml")) {
            return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<user> " + shoppingCart.getUser() + "</user>"
                    + "<itens> " + shoppingCart.getItems() + "</itens>";
        }
        return "";

    }
}

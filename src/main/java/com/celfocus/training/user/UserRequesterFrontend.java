package com.celfocus.training.user;

import java.util.Date;

import com.celfocus.training.Constants;
import com.celfocus.training.Saver;
import com.celfocus.training.entites.ItemInfo;
import com.celfocus.training.entites.ShoppingCart;
import com.celfocus.training.entites.User;
import com.celfocus.training.exceptions.ItemNotFoundException;
import com.celfocus.training.exceptions.ShoppingCartNotFound;
import com.celfocus.training.exceptions.UserNotFoundException;

/**
 * User For Frontent
 */
public class UserRequesterFrontend {

    Saver saver = new Saver();

    public String frontendUser(String type, User user) {
        String name = user.getName();
        Date birthDate = user.getBirthDate();
        boolean older = user.getAge() < Constants.olderAge;
        String bodyPage = "";

        if (type.equals("html")) {
            bodyPage = "<div>"
             + "<h1>User</h1>"
             + "<span>" + name + "</span>"
             + "<span>" + birthDate + "</span>"
             + "<span>" + older + "</span>"
             + "</div>";
        } else if (type.equals("xml")) {
            bodyPage =  "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
            + "<name> " + name + "</name>"
            + "<bd>" + birthDate + "</bd>"
            + "<older> " + older + "</older>";
        }
        return bodyPage;
    }

    public String returnFrontendShoppingCart(String type, ShoppingCart shoppingCart) {
        return frontEndFormat(type, "ShoppingCart", shoppingCart.getUser().toString(), shoppingCart.getItems().toString());
    }

    public String returnFrontendItem(String type, ItemInfo item) {
        return frontEndFormat(type, "Item", item.getName(), Double.toString(item.getValue()));
    }

    private String frontEndFormat(String type, String title, String name, String item) {
        String bodyPage = "";

        if (type.equals("html")) {
            bodyPage = "<div>"
                + "<h1>" + title + "</h1>"
                + "<span> " + name + "</span>"
                + "<span> " + item + "</span>"
                + "</div>";
        } else if (type.equals("xml")) {
            bodyPage = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                + "<user> " + name + "</user>"
                + "<items> " + item + "</items>";
        }
        return bodyPage;
    }

    public void updateUser(String name, String birthDate) throws UserNotFoundException {
        saver.getAccessUser().updateUser(name, birthDate);
    }

    public void createUser(String name, String birthDate) {
        saver.getAccessUser().saveUser(name, birthDate);
    }

    public void deleteUser(String name, String birthDate) throws UserNotFoundException {
        saver.getAccessUser().deleteUser(name);
    }

    public void addItemShopping(String user, String nameItem, int quantity) throws UserNotFoundException, ShoppingCartNotFound,
        ItemNotFoundException {

        nameItem = nameItem.toLowerCase().concat("_item");

        saver.getAccessShopping().addItemToCart(user, nameItem, quantity);
    }
}
package com.celfocus.training.user;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.celfocus.training.Saver;
import com.celfocus.training.models.ItemInfo;
import com.celfocus.training.models.ShoppingCart;
import com.celfocus.training.models.User;
import com.celfocus.training.util.Utils;

/**
 * User For Frontent
 */
public class UserRequesterFrontend {

    /**
     * Metodo utilizado para retornar o Usuario no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param user usuario que será renderizado
     * @return o texto no formato solicitado com as informarções do user
     */
    public String returnFrontendUser(String type, User user) {
        if (type.equals("html")) {
            return "<div>"
             + "<h1>User</h1>"
             + "<span>" + user.getName() + "</span>"
             + "<span>" + user.getBirthDay() + "</span>"
             + "<span>" + user.getIsOlder() + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<name> " + user.getName() + "</name>"
                    + "<bd>" + user.getBirthDay() + "</bd>"
                    + "<older> " + user.getIsOlder() + "</older>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    /**
     * Metodo utilizado para retornar o Shoppingcart no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param shoppingCart shoppingCart que será renderizado
     * @return o texto no formato solicitado com as informarções do shoppingCart
     */
    public String returnFrontendShoppingCart(String type, ShoppingCart shoppingCart) {
        if (type.equals("html")) {
            return "<div>"
             + "<h1>ShoppingCart</h1>"
             + "<span> " + shoppingCart.getUser() + "</span>"
             + "<span> " + shoppingCart.getItens() + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<user> " + shoppingCart.getUser() + "</user>"
                    + "<itens> " + shoppingCart.getItens() + "</itens>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    /**
     * Metodo utilizado para retornar o Item no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param item item que será renderizado
     * @return o texto no formato solicitado com as informarções do item
     */
    public String returnFrontendItem(String type, ItemInfo item) {
        if (type.equals("html")) {
            return "<div>"
             + "<h1>Item</h1>"
             + "<span> " + item.getName() + "</span>"
             + "<span> " + item.getValue() + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<name> " + item.getName() + "</name>"
                    + "<valor> " + item.getValue() + "</valor>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    /**
     * Cria ou atualiza usuario
     * @param arg0
     * @param arg1
     * @param arg2
     */
    public void createOrUpdateUser(String arg0, String arg1, String arg2) {
        Saver saver = new Saver();

        arg0 = arg0.toUpperCase();

        Date d = Utils.toDate(arg1, new SimpleDateFormat("dd/mm/yyyy"));
        if (new Date().getYear() - d.getYear() < 65) {
            arg2 = "false";
        }

        saver.saveOrUpdateUser(arg0, Utils.toDate(arg1, new SimpleDateFormat("dd/mm/yyyy")), arg2.equals("true") ? true : false);
    }
}
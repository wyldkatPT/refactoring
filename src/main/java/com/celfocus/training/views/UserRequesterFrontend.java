package com.celfocus.training.views;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.celfocus.training.models.Product;
import com.celfocus.training.controllers.Saver;
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
     * @return o texto no formato solicitado com as informarções do views
     */
    public String getFrontendUser(String type, User user) {
        if (type.equals("html")) {
            return "<div>"
             + "<h1>User</h1>"
             + "<span>" + user.getName() + "</span>"
             + "<span>" + user.getBirthday() + "</span>"
             + "<span>" + user.isMoreThan18() + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<name> " + user.getBirthday() + "</name>"
                    + "<bd>" + user.getBirthday() + "</bd>"
                    + "<older> " + user.isMoreThan18() + "</older>";
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
    public String getFrontendShoppingCart(String type, ShoppingCart shoppingCart) {
        if (type.equals("html")) {
            return "<div>"
             + "<h1>ShoppingCart</h1>"
             + "<span> " + shoppingCart.getUser() + "</span>"
             + "<span> " + shoppingCart.getCartItems() + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<views> " + shoppingCart.getUser() + "</views>"
                    + "<itens> " + shoppingCart.getCartItems() + "</itens>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    /**
     * Metodo utilizado para retornar o Item no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param product producto que será renderizado
     * @return o texto no formato solicitado com as informarções do item
     */
    public String getFrontendItem(String type, Product product) {
        if (type.equals("html")) {
            return "<div>"
             + "<h1>Item</h1>"
             + "<span> " + product.getName() + "</span>"
             + "<span> " + product.getValue() + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<name> " + product.getName() + "</name>"
                    + "<value> " + product.getValue()+ "</value>";
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

    /**
     * Remover Usuario
     */
    public void deleteUser(String arg0) {
        Saver saver = new Saver();
        saver.deleteUserOrNot(arg0);
    }

    /**
     * Adicionar item ao carrinho
     */
    public void aitemShopping(String user, String nameItem, int qt) {
        Saver saver = new Saver();

        nameItem = nameItem.toLowerCase().concat("_item");

        saver.aIU(user, nameItem, qt);
    }

}
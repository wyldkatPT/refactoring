package com.celfocus.training.user;

import com.celfocus.training.*;
import com.celfocus.training.util.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User For Frontent
 */
public class UserRequesterFrontend {

    /**
     * Metodo utilizado para retornar o Usuario no formato do frontend solicitado
     *
     * @param type tipo do frontend utilizado
     * @param user usuario que será renderizado
     * @return o texto no formato solicitado com as informarções do user
     */
    public String returnFrontendUser(String type, User user) {
        if (type.equals("html")) {
            return "<div>"
                    + "<h1>User</h1>"
                    + "<span>" + user.getNameOfUser() + "</span>"
                    + "<span>" + user.getBirthdayDate() + "</span>"
                    + "<span>" + user.getAge() + "</span>"
                    + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                        + "<name> " + user.getNameOfUser() + "</name>"
                        + "<bd>" + user.getBirthdayDate() + "</bd>"
                        + "<older> " + user.getAge() + "</older>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    /**
     * Metodo utilizado para retornar o Shoppingcart no formato do frontend solicitado
     *
     * @param type         tipo do frontend utilizado
     * @param shoppingCart shoppingCart que será renderizado
     * @return o texto no formato solicitado com as informarções do shoppingCart
     */
    public String returnFrontendShoppingCart(String type, ShoppingCart shoppingCart) {
        if (type.equals("html")) {
            return "<div>"
                    + "<h1>ShoppingCart</h1>"
                    + "<span> " + shoppingCart.getUser() + "</span>"
                    + "<span> " + shoppingCart.getItems() + "</span>"
                    + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                        + "<user> " + shoppingCart.getUser() + "</user>"
                        + "<itens> " + shoppingCart.getItems() + "</itens>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    /**
     * Metodo utilizado para retornar o ItemInfo no formato do frontend solicitado
     *
     * @param type tipo do frontend utilizado
     * @param item item que será renderizado
     * @return o texto no formato solicitado com as informarções do item
     */
    public String returnFrontendItem(String type, ItemInfo item) {
        if (type.equals("html")) {
            return "<div>"
                    + "<h1>ItemInfo</h1>"
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
     *
     * @param arg0
     * @param arg1
     * @param arg2
     */
    public void createOrUpdateUser(String arg0, String arg1, String arg2) {
        Store Store = new Store();

        arg0 = arg0.toUpperCase();

        Date d = Utils.toDate(arg1, new SimpleDateFormat("dd/mm/yyyy"));
        if (new Date().getYear() - d.getYear() < 65) {
            arg2 = "false";
        }

        Store.saveOrUpdateUser(arg0, Utils.toDate(arg1, new SimpleDateFormat("dd/mm/yyyy")));
    }

    /**
     * Remover Usuario
     */
    public void deleteUser(String arg0) {
        Store store = new Store();
        store.deleteUser(arg0);
    }

    /**
     * Adicionar item ao carrinho
     */
    public void aitemShopping(String user, String nameItem, int qt) {
        Store saver = new Store();

        nameItem = nameItem.toLowerCase().concat("_item");

        saver.addItemUser(user, nameItem, qt);
    }

}
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
    public String FrontendUser(String type, User user) {
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
    public String FrontendShoppingCart(String type, ShoppingCart shoppingCart) {
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
    public String FrontendItem(String type, ItemInfo item) {
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
     * @param name
     * @param string_date
     */
    public void createOrUpdateUser(String name, String string_date) {
        Store Store = new Store();

        name = name.toUpperCase();

        Date date = Utils.toDate(string_date, new SimpleDateFormat("dd/mm/yyyy"));

        Store.saveOrUpdateUser(name, date);
    }

    /**
     * Remover Usuario
     */
    public void deleteUser(String name) {
        Store store = new Store();
        store.deleteUser(name);
    }

    /**
     * Adicionar item ao carrinho
     */
    public void additem(String user, String nameItem, int quantity) {
        Store saver = new Store();

        nameItem = nameItem.toLowerCase().concat("_item");

        saver.addItemUser(user, nameItem, quantity);
    }

}
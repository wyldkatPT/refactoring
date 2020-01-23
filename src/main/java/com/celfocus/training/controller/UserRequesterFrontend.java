package com.celfocus.training.controller;

import com.celfocus.training.Saver;
import com.celfocus.training.entity.User;
import com.celfocus.training.entity.cart.ItemInfo;
import com.celfocus.training.entity.cart.ShoppingCart;
import com.celfocus.training.util.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User For Frontent
 */
public class UserRequesterFrontend implements IUserRequesterFrontend {

    private static final String HTML = "html";
    private static final String XML = "xml";

    /**
     * Metodo utilizado para retornar o Usuario no formato do frontend solicitado
     *
     * @param type tipo do frontend utilizado
     * @param user usuario que será renderizado
     * @return o texto no formato solicitado com as informarções do user
     */
    @Override
    public String returnFrontendUser(String type, User user) {
        switch (type) {
            case HTML:
                return this.getUserHtmlString(user);
            case XML:
                return this.getUserXmlString(user);
            default:
                return "";
        }
    }

    /**
     * Metodo utilizado para retornar o Shoppingcart no formato do frontend solicitado
     *
     * @param type         tipo do frontend utilizado
     * @param shoppingCart shoppingCart que será renderizado
     * @return o texto no formato solicitado com as informarções do shoppingCart
     */
    @Override
    public String returnFrontendShoppingCart(String type, ShoppingCart shoppingCart) {
        switch (type) {
            case HTML:
                return this.getCartHtmlString(shoppingCart);
            case XML:
                return this.getCartXmlString(shoppingCart);
            default:
                return "";
        }
    }

    /**
     * Metodo utilizado para retornar o Item no formato do frontend solicitado
     *
     * @param type tipo do frontend utilizado
     * @param item item que será renderizado
     * @return o texto no formato solicitado com as informarções do item
     */
    @Override
    public String returnFrontendItem(String type, ItemInfo item) {
        switch (type) {
            case HTML:
                return this.getItemHtmlString(item);
            case XML:
                return this.getItemXmlString(item);
            default:
                return "";
        }
    }

    /**
     * Cria ou atualiza usuario
     *
     * @param name
     * @param birthDate
     * @param isUserOlder
     */
    @Override
    public void createOrUpdateUser(String name, String birthDate, String isUserOlder) {
        Saver saver = new Saver();

        name = name.toUpperCase();

        Date d = Utils.toDate(birthDate, new SimpleDateFormat("dd/mm/yyyy"));
        if (new Date().getYear() - d.getYear() < 65) {
            isUserOlder = "false";
        }

        saver.saveOrUpdateUser(name, Utils.toDate(birthDate, new SimpleDateFormat("dd/mm/yyyy")), isUserOlder.equals("true"));
    }

    /**
     * Remover Usuario
     */
    @Override
    public void deleteUser(String arg0) {
        Saver saver = new Saver();
        saver.deleteUserOrNot(arg0);
    }

    /**
     * Adicionar item ao carrinho
     */
    @Override
    public void addItemToShoppingCart(String user, String nameItem, int quantity) {
        Saver saver = new Saver();

        nameItem = nameItem.toLowerCase().concat("_item");

        saver.addItemToUser(user, nameItem, quantity);
    }

    private String getUserXmlString(User user) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
            + "<name> " + user.getName() + "</name>"
            + "<bd>" + user.getBirthday() + "</bd>"
            + "<older> " + user.isNotMinor() + "</older>";
    }

    private String getUserHtmlString(User user) {
        return "<div>"
            + "<h1>User</h1>"
            + "<span>" + user.getName() + "</span>"
            + "<span>" + user.getBirthday() + "</span>"
            + "<span>" + user.isNotMinor() + "</span>"
            + "</div>";
    }

    private String getCartXmlString(ShoppingCart shoppingCart) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
            + "<user> " + shoppingCart.getUser() + "</user>"
            + "<itens> " + shoppingCart.getItems() + "</itens>";
    }

    private String getCartHtmlString(ShoppingCart shoppingCart) {
        return "<div>"
            + "<h1>ShoppingCart</h1>"
            + "<span> " + shoppingCart.getUser() + "</span>"
            + "<span> " + shoppingCart.getItems() + "</span>"
            + "</div>";
    }

    private String getItemXmlString(ItemInfo item) {
        return "<name> " + item.getName() + "</name>"
            + "<valor> " + item.getValor() + "</valor>";
    }

    private String getItemHtmlString(ItemInfo item) {
        return "<div>"
            + "<h1>Item</h1>"
            + "<span> " + item.getName() + "</span>"
            + "<span> " + item.getValor() + "</span>"
            + "</div>";
    }

}
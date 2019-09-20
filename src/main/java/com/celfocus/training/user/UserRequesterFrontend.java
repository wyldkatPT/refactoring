package com.celfocus.training.user;
import java.util.Date;
import com.celfocus.training.controller.UserController;

/**
 * User For Frontend
 */
public class UserRequesterFrontend {

    private UserController userController;

    public  UserRequesterFrontend(UserController userController){
        this.userController = userController;
    }

   /* *//**
     * Metodo utilizado para retornar o Usuario no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param user usuario que será renderizado
     * @return o texto no formato solicitado com as informarções do user
     *//*
    public String returnFrontendUser(String type, User user) {
        if (type.equals("html")) {
            return "<div>"
             + "<h1>User</h1>"
             + "<span>" + user.getName() + "</span>"
             + "<span>" + user.getBirthDate()+ "</span>"
             + "<span>" + user.isOlder() + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<name> " + user.getName() + "</name>"
                    + "<bd>" + user.getBirthDate() + "</bd>"
                    + "<older> " + user.isOlder()+ "</older>";
            } else {
                //do nothing
                return "";

        }
    }

    *//**
     * Metodo utilizado para retornar o Shoppingcart no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param shoppingCart shoppingCart que será renderizado
     * @return o texto no formato solicitado com as informarções do shoppingCart
     *//*
    public String returnFrontendShoppingCart (String type, ShoppingCart shoppingCart) {
        if (type.equals("html")) {
            return "<div>"
             + "<h1>ShoppingCart</h1>"
             + "<span> " + shoppingCart.user + "</span>"
             + "<span> " + shoppingCart.itens + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<user> " + shoppingCart.user + "</user>"
                    + "<itens> " + shoppingCart.itens + "</itens>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    *//**
     * Metodo utilizado para retornar o Item no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param item item que será renderizado
     * @return o texto no formato solicitado com as informarções do item
     *//*
    public String returnFrontendItem(String type, ItemInfo item) {
        if (type.equals("html")) {
            return "<div>"
             + "<h1>Item</h1>"
             + "<span> " + item.name + "</span>"
             + "<span> " + item.valor + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<name> " + item.name + "</name>"
                    + "<valor> " + item.valor + "</valor>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    *//**
     * Cria ou atualiza usuario
     * @param name
     * @param birthDate
     * @param isOlder
     */
    public void createOrUpdateUser(String name, Date birthDate, boolean isOlder) {
        userController.saveOrUpdate(name,birthDate,isOlder);
        }

    /**
     * Remove User
     */
    public void deleteUser(String name) {
        userController.deleteUser(name);
    }

  /*  *//**
     * Adicionar item ao carrinho
     *//*
    public void aitemShopping(String user, String nameItem, int qt) {
        Saver saver = new Saver();

        nameItem = nameItem.toLowerCase().concat("_item");

        saver.aIU(user, nameItem, qt);
    }*/

}
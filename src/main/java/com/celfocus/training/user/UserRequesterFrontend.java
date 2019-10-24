package com.celfocus.training.user;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.celfocus.training.item.ItemInfo;
import com.celfocus.training.shoppingcart.ShoppingCart;
import com.celfocus.training.util.Utils;

/**
 * User For Frontent
 */
public class UserRequesterFrontend {

    private UserRequestFactory userRequestFactory;
    private UserService userService;

    public UserRequesterFrontend(UserRequestFactory userRequestFactory, UserService userService) {
        this.userRequestFactory = userRequestFactory;
        userService = userService;
    }

    /**
     * Metodo utilizado para retornar o Usuario no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param user usuario que será renderizado
     * @return o texto no formato solicitado com as informarções do user
     */
    public String returnFrontendUser(String type, User user) {
        return userRequestFactory.strategy(type).returnFrontendUser(user);
    }

    /**
     * Metodo utilizado para retornar o Shoppingcart no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param shoppingCart shoppingCart que será renderizado
     * @return o texto no formato solicitado com as informarções do shoppingCart
     */
    public String returnFrontendShoppingCart(String type, ShoppingCart shoppingCart) {
        return userRequestFactory.strategy(type).returnFrontendShoppingCart(shoppingCart);
    }

    /**
     * Metodo utilizado para retornar o Item no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param item item que será renderizado
     * @return o texto no formato solicitado com as informarções do item
     */
    public String returnFrontendItem(String type, ItemInfo item) {
        return userRequestFactory.strategy(type).returnFrontendItem(item);
    }

    /**
     * Cria ou atualiza usuario
     * @param arg0
     * @param arg1
     * @param arg2
     */
    public void createOrUpdateUser(String arg0, String arg1, String arg2) {
        arg0 = arg0.toUpperCase();

        // TODO business logic outside UI, remove magic number
        Date d = Utils.toDate(arg1, new SimpleDateFormat("dd/mm/yyyy"));
        if (new Date().getYear() - d.getYear() < 65) {
            arg2 = "false";
        }

        userService.saveOrUpdateUser(arg0, Utils.toDate(arg1, new SimpleDateFormat("dd/mm/yyyy")), arg2.equals("true"));
    }

    /**
     * Remover Usuario
     */
    public void deleteUser(String arg0) {
        userService.deleteUser(arg0);
    }

    /**
     * Adicionar item ao carrinho
     */
    public void aitemShopping(String user, String nameItem, int qt) {
        nameItem = nameItem.toLowerCase().concat("_item");
        userService.addItemToShoppingCart(user, nameItem, qt);
    }

}
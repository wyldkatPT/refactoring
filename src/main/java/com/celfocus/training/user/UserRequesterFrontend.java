package com.celfocus.training.user;

import com.celfocus.training.entity.ItemInfo;
import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.User;
import com.celfocus.training.exceptions.ItemInfoNotFoundException;
import com.celfocus.training.exceptions.ShoppingCartItemNotFoundException;
import com.celfocus.training.exceptions.ShoppingCartNotFoundException;
import com.celfocus.training.exceptions.UserNotFoundException;
import com.celfocus.training.repository.ShoppingCartItemRepository;
import com.celfocus.training.repository.UserRepository;
import com.celfocus.training.util.Utils;
import com.celfocus.training.validators.RepositoriesValidator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * com.celfocus.training.entity.User For Frontent
 */
public class UserRequesterFrontend {

    private UserRepository userRepository = new UserRepository();
    private ShoppingCartItemRepository shoppingCartItemRepository = new ShoppingCartItemRepository();

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
                    + "<h1>com.celfocus.training.entity.User</h1>"
                    + "<span>" + user.name + "</span>"
                    + "<span>" + user.birthDate + "</span>"
                    + "<span>" + user.ifUserIsOlder + "</span>"
                    + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                        + "<name> " + user.name + "</name>"
                        + "<birthDate>" + user.birthDate + "</birthDate>"
                        + "<older> " + user.ifUserIsOlder + "</older>";
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
                    + "<h1>com.celfocus.training.entity.ShoppingCart</h1>"
                    + "<span> " + shoppingCart.user + "</span>"
                    + "<span> " + shoppingCart.items + "</span>"
                    + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                        + "<user> " + shoppingCart.user + "</user>"
                        + "<items> " + shoppingCart.items + "</items>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    /**
     * Metodo utilizado para retornar o Item no formato do frontend solicitado
     *
     * @param type tipo do frontend utilizado
     * @param item item que será renderizado
     * @return o texto no formato solicitado com as informarções do item
     */
    public String returnFrontendItem(String type, ItemInfo item) {
        if (type.equals("html")) {
            return "<div>"
                    + "<h1>Item</h1>"
                    + "<span> " + item.name + "</span>"
                    + "<span> " + item.value + "</span>"
                    + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<name> " + item.name + "</name>"
                        + "<value> " + item.value + "</value>";
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
        RepositoriesValidator repositoriesValidator = new RepositoriesValidator();

        arg0 = arg0.toUpperCase();

        Date d = Utils.toDate(arg1, new SimpleDateFormat("dd/mm/yyyy"));
        if (new Date().getYear() - d.getYear() < 65) {
            arg2 = "false";
        }

        userRepository.saveOrUpdateUser(arg0, Utils.toDate(arg1, new SimpleDateFormat("dd/mm/yyyy")), arg2.equals("true") ? true : false);
    }

    /**
     * Remover Usuario
     */
    public void deleteUser(String arg0) {
        RepositoriesValidator repositoriesValidator = new RepositoriesValidator();
        userRepository.deleteUserOrNot(arg0);
    }

    /**
     * Adicionar item ao carrinho
     */
    public void aitemShopping(String userName, String itemName, int qt) throws UserNotFoundException, ItemInfoNotFoundException, ShoppingCartItemNotFoundException, ShoppingCartNotFoundException {
        RepositoriesValidator repositoriesValidator = new RepositoriesValidator();

        itemName = itemName.toLowerCase().concat("_item");

        shoppingCartItemRepository.addItemToShoppingCart(userName, itemName, qt);
    }

}
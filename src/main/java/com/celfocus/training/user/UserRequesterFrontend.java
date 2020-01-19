package com.celfocus.training.user;

import com.celfocus.training.entity.ItemInfo;
import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.User;
import com.celfocus.training.service.ItemInfoService;
import com.celfocus.training.service.ItemInfoServiceI;
import com.celfocus.training.service.UserService;
import com.celfocus.training.service.UserServiceI;
import com.celfocus.training.util.Utils;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User For Frontent
 */
public class UserRequesterFrontend {

	public static final String HTML = "html";
	public static final String XML = "xml";

	/**
	 * Metodo utilizado para retornar o Usuario no formato do frontend solicitado
	 *
	 * @param type tipo do frontend utilizado
	 * @param user usuario que será renderizado
	 * @return o texto no formato solicitado com as informarções do user
	 */
	public String returnFrontendUser(String type, User user) {
		if (type.equals(HTML)) {
			return "<div>"
					+ "<h1>User</h1>"
					+ "<span>" + user.getUsername() + "</span>"
					+ "<span>" + user.getBirthday() + "</span>"
					+ "<span>" + user.isLegalAge() + "</span>"
					+ "</div>";
		} else {
			if (type.equals(XML)) {
				return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
						+ "<name> " + user.getUsername() + "</name>"
						+ "<bd>" + user.getBirthday() + "</bd>"
						+ "<older> " + user.isLegalAge() + "</older>";
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
		if (type.equals(HTML)) {
			return "<div>"
					+ "<h1>ShoppingCart</h1>"
					+ "<span> " + shoppingCart.getUser() + "</span>"
					+ "<span> " + shoppingCart.getShoppingCartItems() + "</span>"
					+ "</div>";
		} else {
			if (type.equals(XML)) {
				return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
						+ "<user> " + shoppingCart.getUser() + "</user>"
						+ "<itens> " + shoppingCart.getShoppingCartItems() + "</itens>";
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
		if (type.equals(HTML)) {
			return "<div>"
					+ "<h1>Item</h1>"
					+ "<span> " + item.getItemName() + "</span>"
					+ "<span> " + item.getItemValue() + "</span>"
					+ "</div>";
		} else {
			if (type.equals(XML)) {
				return "<name> " + item.getItemName() + "</name>"
						+ "<valor> " + item.getItemValue() + "</valor>";
			} else {
				//do nothing
				return "";
			}
		}
	}

	/**
	 * Cria ou atualiza usuario
	 *
	 * @param username
	 * @param birthday
	 * @param isLegalAge
	 */
	public void createOrUpdateUser(String username, String birthday, String isLegalAge) {
		UserServiceI userService = new UserService();
		username = username.toUpperCase();

		Date date = Utils.toDate(birthday, new SimpleDateFormat("dd/mm/yyyy"));
		if (new Date().getYear() - date.getYear() < 65) {
			isLegalAge = "false";
		}

		userService.saveOrUpdateUser(username, Utils.toDate(birthday, new SimpleDateFormat("dd/mm/yyyy")),
				isLegalAge.equals("true"));
	}

	/**
	 * Remover Usuario
	 */
	public void deleteUser(String username) {
		UserServiceI userService = new UserService();
		userService.deleteUser(username);
	}

	/**
	 * Adicionar item ao carrinho
	 */
	public void addItemToShoppingCart(String username, String itemName, int quantity) {
		ItemInfoServiceI infoService = new ItemInfoService();

		itemName = itemName.toLowerCase().concat("_item");

		infoService.addItemToShoppingCart(username, itemName, quantity);
	}

}
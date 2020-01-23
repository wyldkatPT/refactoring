package com.celfocus.training.controller;

import com.celfocus.training.constant.FrontendConstant;
import com.celfocus.training.model.ItemInfo;
import com.celfocus.training.model.ShoppingCart;
import com.celfocus.training.model.User;
import com.celfocus.training.repository.UserRepositoryImpl;
import com.celfocus.training.service.UserService;
import com.celfocus.training.service.UserServiceManagement;

public class UserRequesterFrontend {

	private UserRepositoryImpl userRepositoryImpl;
	private UserService userService;

	public UserRequesterFrontend(UserRepositoryImpl userRepositoryImpl, UserServiceManagement userService) {
		this.userRepositoryImpl = userRepositoryImpl;
		this.userService = userService;
	}

	public String returnFrontendUser(String type, User user) {
		if (FrontendConstant.HTML.equals(type)) {
			return "<div>"
					+ "<h1>User</h1>"
					+ "<span>" + user.name + "</span>"
					+ "<span>" + user.birthdayDate + "</span>"
					+ "</div>";
		}
		if (FrontendConstant.XML.equals(type)) {
			return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
					+ "<name> " + user.name + "</name>"
					+ "<bd>" + user.birthdayDate + "</bd>";
		}
		return "";
	}

	public String returnFrontendShoppingCart(String type, ShoppingCart shoppingCart) {
		if (FrontendConstant.HTML.equals(type)) {
			return "<div>"
					+ "<h1>ShoppingCart</h1>"
					+ "<span> " + shoppingCart.user + "</span>"
					+ "<span> " + shoppingCart.shoppingCartItems + "</span>"
					+ "</div>";
		}
		if (FrontendConstant.XML.equals(type)) {
			return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
					+ "<user> " + shoppingCart.user + "</user>"
					+ "<itens> " + shoppingCart.shoppingCartItems + "</itens>";
		}
		return "";
	}

	public String returnFrontendItem(String type, ItemInfo item) {
		if (FrontendConstant.HTML.equals(type)) {
			return "<div>"
					+ "<h1>Item</h1>"
					+ "<span> " + item.name + "</span>"
					+ "<span> " + item.value + "</span>"
					+ "</div>";
		}
		if (FrontendConstant.XML.equals(type)) {
			return "<name> " + item.name + "</name>"
					+ "<valor> " + item.value + "</valor>";
		}
		return "";
	}

	public void createUser(String userName, String birthdayDate) {
		userService.insertUser(userName, birthdayDate);
	}

	public void updateUser(String userName, String birthdayDate) {
		userService.updateUser(userName, birthdayDate);
	}

	public void deleteUser(String userName) {
		userService.removeUser(userName);
	}

	public void addItemToShoppingCart(String user, String itemName, int quantity) {
		userService.addItem(user, itemName, quantity);
	}

}
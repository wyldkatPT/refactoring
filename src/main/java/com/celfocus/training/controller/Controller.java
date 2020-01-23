package com.celfocus.training.controller;

import com.celfocus.training.entity.User;
import com.celfocus.training.exception.UserNotFoundException;
import com.celfocus.training.service.ShoppingCartService;
import com.celfocus.training.service.ShoppingCartServiceI;
import com.celfocus.training.service.UserServiceI;
import java.util.Date;

public class Controller {

	private UserServiceI userService;
	private ShoppingCartServiceI shoppingCartService;

	public Controller(UserServiceI userService, ShoppingCartService shoppingCartService) {
		this.userService = userService;
		this.shoppingCartService = shoppingCartService;
	}

	public void createUser(String username, Date birthday, boolean isLegalAge) throws UserNotFoundException {

		User user = userService.saveOrUpdateUser(username, birthday, isLegalAge);
		shoppingCartService.addUserToShoppingCart(user);

	}

}

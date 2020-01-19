package com.celfocus.training.service;

import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserService implements UserServiceI {

	private static final List<User> users = new ArrayList<>();
	private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();

	@Override
	public void saveOrUpdateUser(String username, Date birthday, boolean isLegalAge) {
		if (userExists(username)) {
			User user = findUser(username);
			user.setBirthday(birthday);
			user.setLegalAge(isLegalAge);
			ShoppingCart shoppingCartFound = null;

			for (ShoppingCart shoppingCart : shoppingCarts) {
				if (shoppingCart.getUser().equals(user)) {
					shoppingCartFound = shoppingCart;
				}
			}

			if (shoppingCartFound == null) {
				ShoppingCart shoppingCart = new ShoppingCart();
				shoppingCart.setUser(user);
				shoppingCarts.add(shoppingCart);
			}

			users.add(user);
		} else {
			User user = new User();
			user.setBirthday(birthday);
			user.setUsername(username);
			user.setLegalAge(isLegalAge);
			users.add(user);
			ShoppingCart shoppingCart = new ShoppingCart(user, new ArrayList<>());
			shoppingCarts.add(shoppingCart);
		}
	}

	private boolean userExists(String username) {
		User userFound = null;
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				userFound = user;
			}
		}
		return userFound != null;
	}

	private User findUser(String name) {
		User userFound = null;
		for (User user : users) {
			if (user.getUsername().equals(name)) {
				userFound = user;
			}
		}
		return userFound;
	}

	@Override
	public void deleteUser(String username) {
		User userFound = null;
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				userFound = user;
			}
		}
		if (userFound != null) {
			users.remove(userFound);
		}
	}
}

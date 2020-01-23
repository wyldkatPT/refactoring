package com.celfocus.training.service;

import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.User;
import com.celfocus.training.exception.UserNotFoundException;
import com.celfocus.training.repository.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserService implements UserServiceI {

	private static final List<User> users = new ArrayList<>();
	private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
	private UserRepository userRepository;

	public UserService(UserRepository repository) {
		this.userRepository = repository;
	}

	@Override
	public User saveOrUpdateUser(String username, Date birthday, boolean isLegalAge) throws UserNotFoundException {

			if (userRepository.userExists(username)) {
				return userRepository.updateUser(username, birthday);
			} else {
				User user = new User();
				user.setUsername(username);
				user.setBirthday(birthday);
				return user;
			}



	/*	if (userRepository.userExists(username)) {
			User user = null;
			try {
				user = userRepository.findUser(username);
			} catch (UserNotFoundException e) {
				e.printStackTrace();
			}

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
			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setUser(user);
			shoppingCarts.add(shoppingCart);
		}*/
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

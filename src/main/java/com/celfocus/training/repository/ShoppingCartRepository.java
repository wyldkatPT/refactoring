package com.celfocus.training.repository;

import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.User;
import com.celfocus.training.exception.UserNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartRepository {

	List<ShoppingCart> shoppingCarts = new ArrayList<>();

	public boolean hasUser(User user) {
		for (ShoppingCart shoppingCartEntry : shoppingCarts) {
			if (shoppingCartEntry.getUser().getUsername().equals(user.getUsername())) {
				return true;
			}
		}
		return false;
	}

	public User getUser(String username) throws UserNotFoundException {
		for (ShoppingCart shoppingCartEntry : shoppingCarts) {
			if (shoppingCartEntry.getUser().getUsername().equals(username)) {
				return shoppingCartEntry.getUser();
			}
		}
		throw new UserNotFoundException();
	}

	public void addUser(User user) {
		ShoppingCart shoppingCart = new ShoppingCart() {{
			addUser(user);
		}};

		shoppingCarts.add(shoppingCart);
	}

}

package com.celfocus.training.service;

import com.celfocus.training.entity.ItemInfo;
import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.ShoppingCartItem;
import com.celfocus.training.entity.User;
import com.celfocus.training.repository.ShoppingCartRepository;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartService implements ShoppingCartServiceI {

	private static final List<User> users = new ArrayList<>();
	private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
	private static final List<ItemInfo> itemInfos = new ArrayList<>();

	private User user;
	private ShoppingCart shoppingCart;
	private ShoppingCartItem shoppingCartItem;
	private ItemInfo itemInfo;

	private ShoppingCartRepository shoppingCartRepository;

	public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
		this.shoppingCartRepository = shoppingCartRepository;
	}

	public void addUserToShoppingCart(User user) {
		if (!shoppingCartRepository.hasUser(user)) {
			shoppingCartRepository.addUser(user);
		}
	}

	public void removeItemFromShoppingCart(String username, String itemName) {
		User userFound = null;
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				userFound = user;
			}
		}

		if (userFound != null) {
			ShoppingCart shoppingCartFound = null;
			for (ShoppingCart shoppingCart : shoppingCarts) {
				if (shoppingCart.getUser().equals(userFound)) {
					shoppingCartFound = shoppingCart;
				}
			}

			if (shoppingCartFound != null) {
				ShoppingCartItem shoppingCartItemFound = null;
				for (ShoppingCartItem shoppingCartItem : shoppingCartFound.getShoppingCartItems()) {
					if (shoppingCartItem.getItemInfo().getItemName().equals(itemName)) {
						shoppingCartItemFound = shoppingCartItem;
					}
				}

				if (shoppingCartItemFound != null) {
					shoppingCartFound.getShoppingCartItems().remove(shoppingCartItemFound);
				}
			}
		}
	}

}

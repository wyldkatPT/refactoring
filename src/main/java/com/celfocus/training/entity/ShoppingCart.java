package com.celfocus.training.entity;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

	private User user;
	private List<ShoppingCartItem> shoppingCartItems;

	public ShoppingCart() {
		this.shoppingCartItems = new ArrayList<>();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ShoppingCartItem> getShoppingCartItems() {
		return shoppingCartItems;
	}

	public void setShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
		this.shoppingCartItems = shoppingCartItems;
	}
}

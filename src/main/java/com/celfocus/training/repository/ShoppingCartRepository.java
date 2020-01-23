package com.celfocus.training.repository;

import com.celfocus.training.model.ShoppingCart;
import com.celfocus.training.model.ShoppingCartItem;
import com.celfocus.training.model.User;

public interface ShoppingCartRepository {

	ShoppingCart fetchShoppingCartByUser(User user);
	ShoppingCart createShoppingCart(User user);
	void removeShoppingCartItem(ShoppingCart shoppingCart, ShoppingCartItem shoppingCartItem);
}

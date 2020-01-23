package com.celfocus.training.repository;

import com.celfocus.training.model.ShoppingCart;
import com.celfocus.training.model.ShoppingCartItem;
import com.celfocus.training.model.User;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {

	private List<ShoppingCart> shoppingCarts = new ArrayList<>();

	@Override
	public ShoppingCart fetchShoppingCartByUser(User user) {
		return shoppingCarts.stream().filter(shoppingCart -> shoppingCart.user.equals(user)).findFirst().orElse(null);
	}

	@Override
	public ShoppingCart createShoppingCart(User user) {
		return ShoppingCart.builder()
				.user(user)
				.shoppingCartItems(new ArrayList<>())
				.build();
	}

	@Override
	public void removeShoppingCartItem(ShoppingCart shoppingCart, ShoppingCartItem shoppingCartItem) {
		shoppingCart.getShoppingCartItems().remove(shoppingCartItem);
	}
}

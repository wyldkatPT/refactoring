package com.celfocus.training.service;

import com.celfocus.training.model.User;
import com.celfocus.training.repository.ShoppingCartRepository;
import com.celfocus.training.repository.ShoppingCartRepositoryImpl;

public class ShoppingCartServiceManagement implements ShoppingCartService {

	private ShoppingCartRepository shoppingCartRepository;

	public ShoppingCartServiceManagement(ShoppingCartRepositoryImpl shoppingCartRepository) {
		this.shoppingCartRepository = shoppingCartRepository;
	}

	@Override
	public void createShoppingCart(User user) {
		shoppingCartRepository.fetchShoppingCartByUser(user);
	}
}

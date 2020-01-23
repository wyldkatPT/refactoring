package com.celfocus.training.repository;

import com.celfocus.training.model.ItemInfo;
import com.celfocus.training.model.ShoppingCart;
import com.celfocus.training.model.ShoppingCartItem;
import com.celfocus.training.model.ShoppingCartItem.ShoppingCartItemBuilder;

public class ShoppingCartItemRepositoryImpl implements ShoppingCartItemRepository {

	@Override
	public ShoppingCartItem fetchShoppingCartItem(String itemName, ShoppingCart shoppingCart) {
		return shoppingCart.shoppingCartItems.stream()
				.filter(shoppingCartItem -> shoppingCartItem.itemInfo.name.equals(itemName)).findFirst().orElse(null);
	}

	@Override
	public ShoppingCartItem createShoppingCartItem(ItemInfo itemInfo, int quantity, double discountAmount) {
		return ShoppingCartItem.builder()
				.itemInfo(itemInfo)
				.quantity(quantity)
				.discountAmount(discountAmount)
				.build();
	}
}

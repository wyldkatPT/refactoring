package com.celfocus.training.repository;

import com.celfocus.training.model.ItemInfo;
import com.celfocus.training.model.ShoppingCart;
import com.celfocus.training.model.ShoppingCartItem;

public interface ShoppingCartItemRepository {

	ShoppingCartItem fetchShoppingCartItem(String itemName, ShoppingCart shoppingCart);
	ShoppingCartItem createShoppingCartItem(ItemInfo itemInfo, int quantity, double discountAmount);
}

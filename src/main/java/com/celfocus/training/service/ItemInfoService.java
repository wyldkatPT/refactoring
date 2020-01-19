package com.celfocus.training.service;

import com.celfocus.training.entity.ItemInfo;
import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.ShoppingCartItem;
import com.celfocus.training.entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemInfoService implements ItemInfoServiceI {

	public static final double SENIOR_DISCOUNT = 0.1;
	public static final double LEGAL_AGE_DISCOUNT = 0.2;
	public static final int SENIOR_AGE = 80;
	private static final List<User> users = new ArrayList<>();
	private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
	private static final List<ItemInfo> itemInfos = new ArrayList<>();

	@Override
	public void addItemToShoppingCart(String username, String itemName, int quantity) {
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
					shoppingCartItemFound.setQuantity(shoppingCartItemFound.getQuantity() + quantity);
				} else {
					ItemInfo itemFound = null;
					for (ItemInfo item : itemInfos) {
						if (item.getItemName().equals(itemName)) {
							itemFound = item;
						}
					}

					if (itemFound != null) {
						ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
						shoppingCartItem.setItemInfo(itemFound);
						shoppingCartItem.setQuantity(quantity);
						if (userFound.isLegalAge() && (new Date().getYear() - userFound.getBirthday().getYear() < SENIOR_AGE)) {
							shoppingCartItem.setDiscount(LEGAL_AGE_DISCOUNT);
						} else if (userFound.isLegalAge()) {
							shoppingCartItem.setDiscount(SENIOR_DISCOUNT);
						}
					}

				}
			}
		}
	}
}

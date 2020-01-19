package com.celfocus.training.saver;

import com.celfocus.training.entity.ItemInfo;
import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.ShoppingCartItem;
import com.celfocus.training.entity.User;
import java.util.ArrayList;
import java.util.List;

public class Saver {

	private static final List<User> users = new ArrayList<>();
	private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
	private static final List<ItemInfo> itemInfos = new ArrayList<>();

	private User user;
	private ShoppingCart shoppingCart;
	private ShoppingCartItem shoppingCartItem;
	private ItemInfo itemInfo;

	public Saver() {
	}

	public Saver(User user, ShoppingCart shoppingCart, ShoppingCartItem shoppingCartItem,
			ItemInfo itemInfo) {
		this.user = user;
		this.shoppingCart = shoppingCart;
		this.shoppingCartItem = shoppingCartItem;
		this.itemInfo = itemInfo;
	}

	public ItemInfo findItem(String name) {
		ItemInfo itemFound = null;
		for (ItemInfo info : itemInfos) {
			if (info.getItemName().equals(name)) {
				itemFound = info;
			}
		}
		return itemFound;
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

	public void createItemIfItDoesNotExist(String name, double value) {
		ItemInfo itemInfo = null;
		for (ItemInfo info : itemInfos) {
			if (info.getItemName().equals(name)) {
				itemInfo = info;
			}
		}

		if (itemInfo == null) {
			createItemInfo(name, value);
		}
	}

	private void createItemInfo(String name, double value) {
		ItemInfo itemInfo = new ItemInfo(name, value);
		itemInfos.add(itemInfo);
	}

} 
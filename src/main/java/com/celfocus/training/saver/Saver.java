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

} 
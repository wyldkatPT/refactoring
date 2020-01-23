package com.celfocus.training.service;

import com.celfocus.training.constant.AmountConstant;
import com.celfocus.training.model.ItemInfo;
import com.celfocus.training.model.ShoppingCart;
import com.celfocus.training.model.ShoppingCartItem;
import com.celfocus.training.model.User;
import com.celfocus.training.repository.ItemInfoRepository;
import com.celfocus.training.repository.ItemInfoRepositoryImpl;
import com.celfocus.training.repository.ShoppingCartItemRepository;
import com.celfocus.training.repository.ShoppingCartItemRepositoryImpl;
import com.celfocus.training.repository.ShoppingCartRepository;
import com.celfocus.training.repository.ShoppingCartRepositoryImpl;
import com.celfocus.training.repository.UserRepository;
import com.celfocus.training.repository.UserRepositoryImpl;
import com.celfocus.training.util.Utils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class UserServiceManagement implements UserService {

	private UserRepository userRepository;
	private ShoppingCartRepository shoppingCartRepository;
	private ShoppingCartItemRepository shoppingCartItemRepository;
	private ItemInfoRepository itemInfoRepository;
	private Date birthdayDateConverted;
	int currentYear;

	public UserServiceManagement(UserRepositoryImpl userRepository,
			ShoppingCartRepositoryImpl shoppingCartRepository,
			ShoppingCartItemRepositoryImpl shoppingCartItemRepository,
			ItemInfoRepositoryImpl itemInfoRepository) {
		this.userRepository = userRepository;
		this.shoppingCartRepository = shoppingCartRepository;
		this.shoppingCartItemRepository = shoppingCartItemRepository;
		this.itemInfoRepository = itemInfoRepository;
		currentYear = Calendar.getInstance().get(Calendar.YEAR);
	}

	public void insertUser(String userName, String birthdayDate) {
		Utils.toDate(birthdayDate, new SimpleDateFormat("dd/mm/yyyy"));

		if (!userRepository.isUserFound(userName)) {
			userRepository.createUser(userName, birthdayDateConverted);
		}
	}

	public void updateUser(String userName, String birthdayDate) {
		birthdayDateConverted = Utils.toDate(birthdayDate, new SimpleDateFormat("dd/mm/yyyy"));

		if (userRepository.isUserFound(userName)) {
			User user = userRepository.fetchUser(userName);
			User updatedUser = userRepository
					.updateUser(user, birthdayDateConverted);

			if (Objects.isNull(shoppingCartRepository.fetchShoppingCartByUser(updatedUser))) {
				shoppingCartRepository.createShoppingCart(updatedUser);
			}
		}
	}

	public void removeUser(String userName) {
		userRepository.deleteUser(userName);
	}

	public void addItem(String userName, String itemName, int quantity) {
		User user = userRepository.fetchUser(userName);

		ShoppingCart shoppingCart = shoppingCartRepository.fetchShoppingCartByUser(user);

		ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.fetchShoppingCartItem(itemName, shoppingCart);

		if (Objects.isNull(shoppingCartItem)) {
			ItemInfo itemInfo = itemInfoRepository.fetchItemInfo(itemName);

			if (!Objects.isNull(itemInfo)) {
				if (currentYear - user.birthdayDate.getYear() < AmountConstant.EIGHTY_YEARS) {
					ShoppingCartItem createdShoppingCartItem = shoppingCartItemRepository
							.createShoppingCartItem(itemInfo, quantity, AmountConstant.DISCOUNT_FOR_LESS_THAN_EIGHTY_YEARS);
					shoppingCart.getShoppingCartItems().add(createdShoppingCartItem);
				} else {
					ShoppingCartItem createdShoppingCartItem = shoppingCartItemRepository
							.createShoppingCartItem(itemInfo, quantity, AmountConstant.DISCOUNT_FOR_MORE_THAN_EIGHTY_YEARS);
					shoppingCart.getShoppingCartItems().add(createdShoppingCartItem);
				}
			}
		}

		shoppingCartItem.quantity += quantity;
	}

	public void removeShoppingCartItem(String userName, String itemName) {
		if (userRepository.isUserFound(userName)) {
			User user = userRepository.fetchUser(userName);

			if (!Objects.isNull(user)) {
				ShoppingCart shoppingCart = shoppingCartRepository.fetchShoppingCartByUser(user);

				if (!Objects.isNull(shoppingCart)) {
					ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.fetchShoppingCartItem(itemName, shoppingCart);

					if (!Objects.isNull(shoppingCartItem)) {
						shoppingCartRepository.removeShoppingCartItem(shoppingCart, shoppingCartItem);
					}
				}
			}
		}
	}


//	public void createItemInfo(String arg0, double v) {
//		ItemInfo f = null;
//		for (ItemInfo i : itemsInfo) {
//			if (i.name == arg0) {
//				f = i;
//			}
//		}
//
//		if (f != null) {
//
//		} else {
//			ItemInfo ift = new ItemInfo();
//			ift.name = arg0;
//			ift.valor = v;
//			itemsInfo.add(ift);
//		}
//	}
}

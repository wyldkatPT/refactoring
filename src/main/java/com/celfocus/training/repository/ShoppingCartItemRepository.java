package com.celfocus.training.repository;

import com.celfocus.training.entity.ItemInfo;
import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.ShoppingCartItem;
import com.celfocus.training.entity.User;
import com.celfocus.training.exceptions.ItemInfoNotFoundException;
import com.celfocus.training.exceptions.ShoppingCartItemNotFoundException;
import com.celfocus.training.exceptions.ShoppingCartNotFoundException;
import com.celfocus.training.exceptions.UserNotFoundException;
import com.celfocus.training.validators.RepositoriesValidator;

public class ShoppingCartItemRepository {

    private UserRepository userRepository = new UserRepository();
    private ShoppingCartRepository shoppingCartRepository = new ShoppingCartRepository();
    private ShoppingCartItemRepository shoppingCartItemRepository = new ShoppingCartItemRepository();
    private ItemRepository itemRepository = new ItemRepository();
    private RepositoriesValidator repositoriesValidator = new RepositoriesValidator();

    private ShoppingCartItem findShoppingCartItemByItemName(String itemName, ShoppingCart userShoppingCart) {
        ShoppingCartItem userShoppingCartItem = null;
        for (ShoppingCartItem shoppingCartItem : userShoppingCart.items) {
            if (shoppingCartItem.item.name.equals(itemName)) {
                userShoppingCartItem = shoppingCartItem;
            }
        }
        return userShoppingCartItem;
    }

    public void removeItemFromShoppingCart(String userName, String nameItem) throws UserNotFoundException, ShoppingCartNotFoundException, ShoppingCartItemNotFoundException {
        User userFound = userRepository.findUserByName(userName);
        ShoppingCart found = shoppingCartRepository.findShoppingCartByUser(userFound);
        ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.findShoppingCartItemByItemName(nameItem, found);

        if (repositoriesValidator.userShoppingCartItemExists(userFound, found, shoppingCartItem)) {
            found.items.remove(shoppingCartItem);
        }

    }

    public void addItemToShoppingCart(String userName, String itemName, int quantity) throws UserNotFoundException, ShoppingCartNotFoundException, ShoppingCartItemNotFoundException, ItemInfoNotFoundException {

        User user = userRepository.findUserByName(userName);
        ShoppingCart userShoppingCart = shoppingCartRepository.findShoppingCartByUser(user);
        ShoppingCartItem userShoppingCartItem = shoppingCartItemRepository.findShoppingCartItemByItemName(itemName, userShoppingCart);

        if (repositoriesValidator.userShoppingCartItemExists(user, userShoppingCart, userShoppingCartItem)) {

            userShoppingCartItem.quantity += quantity;

            ItemInfo itemInfo = itemRepository.findItemByName(itemName);

            if (itemInfo == null) {
                throw new ItemInfoNotFoundException("No info found on this item in our Database");
            }

            ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
            shoppingCartItem.item = itemInfo;
            shoppingCartItem.quantity = quantity;
            if (user.ifUserIsOlder && repositoriesValidator.isLessThanEightyYearsOld(user)) {
                shoppingCartItem.discount = 0.2;
            } else if (user.ifUserIsOlder) {
                shoppingCartItem.discount = 0.1;
            }
        }
    }

}

package com.celfocus.training.service;

import com.celfocus.training.model.ItemInfo;
import com.celfocus.training.model.ShoppingCart;
import com.celfocus.training.model.ShoppingCartItem;
import com.celfocus.training.model.User;
import com.celfocus.training.repository.ShoppingCartRepository;

import java.util.Objects;

public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ItemService itemService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ItemService itemService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.itemService = itemService;
    }

    public ShoppingCart getShoppingCartByUser(String userName) {
        return shoppingCartRepository.getShoppingCartByUser(userName);
    }

    public ShoppingCartItem getShoppingCartItemByName(ShoppingCart shoppingCart, String itemName) {
        return shoppingCart.getItems().stream().filter(shoppingCartItem -> shoppingCartItem.getItem().getName().equals(itemName)).findFirst().orElse(null);
    }

    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.createShoppingCart(shoppingCart);
    }

    public void addItemToShoppingCart(String userName, String itemName, int itemQuantity) {
        ShoppingCart shoppingCart = getShoppingCartByUser(userName);

        if (Objects.isNull(shoppingCart)) {
            System.out.println("ShoppingCart not found!");
            return;
        }

        ShoppingCartItem shoppingCartItem = getShoppingCartItemByName(shoppingCart, itemName);

        if (Objects.nonNull(shoppingCartItem)) {
            shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() + itemQuantity);
            return;
        }

        ItemInfo foundItem = itemService.getItemByName(itemName);

        if (Objects.isNull(foundItem)) {
            System.out.println("Item not found!");
            return;
        }

        shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setItem(foundItem);
        shoppingCartItem.setQuantity(itemQuantity);
        applyDiscount(shoppingCart, shoppingCartItem);

        shoppingCart.getItems().add(shoppingCartItem);
    }

    private void applyDiscount(ShoppingCart shoppingCart, ShoppingCartItem item) {
        User user = shoppingCart.getUser();
        if (user.isOfLegalAge() && user.hasLessThan80years()) {
            item.setDiscount(0.2);
        } else if (user.isOfLegalAge()) {
            item.setDiscount(0.1);
        }
    }

    public void removeItemFromShoppingCart(String userName, String itemName) {
        ShoppingCart shoppingCart = shoppingCartRepository.getShoppingCartByUser(userName);

        if (Objects.nonNull(shoppingCart)) {
            ShoppingCartItem shoppingCartItem = getShoppingCartItemByName(shoppingCart, itemName);

            if (Objects.nonNull(shoppingCartItem)) {
                shoppingCart.getItems().remove(shoppingCartItem);
                return;
            }

            System.out.println("ShoppingCartItem not found!");
        }
        System.out.println("ShoppingCart not found!");
    }
}

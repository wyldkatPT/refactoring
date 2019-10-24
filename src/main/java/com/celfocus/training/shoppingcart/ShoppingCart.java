package com.celfocus.training.shoppingcart;

import com.celfocus.training.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCart {
    public User user;

    public List<ShoppingCartItem> itens;

    public ShoppingCart(User user, List<ShoppingCartItem> itens) {
        this.user = user;
        this.itens = itens;
    }

    public ShoppingCart(User user) {
        this(user, new ArrayList<>());
    }

    public Optional<ShoppingCartItem> findShoppingCartItem(String name) {
        return itens.stream().filter(cartItem -> cartItem.item.name == name).findFirst();
    }

    public void addShoppingCartItem(ShoppingCartItem item) {
        itens.add(item);
    }

    public void removeShoppingCartItem(String itemName) {
        itens.removeIf(cartItem -> cartItem.item.name.equals(itemName));
    }
}

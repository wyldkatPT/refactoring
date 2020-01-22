package com.celfocus.training;

import com.celfocus.training.entity.ItemInfo;
import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.ShoppingCartItem;
import com.celfocus.training.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Temos 4 entidades em nosso projeto User, ShoppingCart, ShoppingCartItem e ItemInfo
 */
public class Saver {

    private static final List<User> users = new ArrayList<>();
    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static final List<ItemInfo> itens = new ArrayList<>();

    public void saveOrUpdateUser(String userName, Date birthDay, boolean isAdult) {

        if (userExists(userName)) {
            User user = getUserFromName(userName);
            user.birthDay = birthDay;
            user.isAdult = isAdult;
            ShoppingCart shoppingCart = getShoppingCartFromUser(user);

            if (shoppingCart == null) {
                ShoppingCart newShoppingCart = new ShoppingCart();
                newShoppingCart.user = user;
                newShoppingCart.shoppingCartItems = new ArrayList<>();
                shoppingCarts.add(newShoppingCart);
            }
            users.add(user);
        } else {
            User user = new User();
            user.birthDay = birthDay;
            user.userName = userName;
            user.isAdult = isAdult;
            users.add(user);
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.user = user;
            shoppingCart.shoppingCartItems = new ArrayList<>();
            shoppingCarts.add(shoppingCart);
        }
    }

    private boolean userExists(String name) {
        return users.stream().anyMatch(user -> user.userName.equals(name));
    }

    private User getUserFromName(String name) {
        return users.stream().filter(user -> user.userName.equals(name)).findAny().orElse(null);
    }

    private ShoppingCart getShoppingCartFromUser(User user) {
        return shoppingCarts.stream().filter(shoppingCart -> shoppingCart.user.equals(user)).findAny().orElse(null);
    }

    private ShoppingCartItem getItemInShoppingCartFromName(ShoppingCart shoppingCart, String itemName) {
        return shoppingCart.shoppingCartItems.stream().filter(itens -> itens.itemInfo.itemName.equals(itemName)).findAny().orElse(null);
    }

    public ItemInfo findItem(String itemName) {
        return itens.stream().filter(item -> item.itemName.equals(itemName)).findAny().orElse(null);
    }

    public void deleteUser(String userName) {
        User user = getUserFromName(userName);
        if (user != null) {
            users.remove(user);
        }
    }

    public void aIU(String userName, String itemName, int qt) {
        User user = getUserFromName(userName);
        if (user == null) return;

        ShoppingCart shoppingCart = getShoppingCartFromUser(user);
        if (shoppingCart == null)  return;

        ShoppingCartItem shoppingCartItem = getItemInShoppingCartFromName(shoppingCart, itemName);

        if (shoppingCartItem != null) {
            shoppingCartItem.quantity += qt;
        } else {
            ItemInfo item = findItem(itemName);
            if (item == null) return;

            ShoppingCartItem newShoppingCartItem = new ShoppingCartItem();
            newShoppingCartItem.itemInfo = item;
            newShoppingCartItem.quantity = qt;
            if (user.isAdult && (new Date().getYear() - user.birthDay.getYear() < 80) ) {
                newShoppingCartItem.discount = 0.2;
            } else if (user.isAdult) {
                newShoppingCartItem.discount = 0.1;
            }
            shoppingCart.shoppingCartItems.add(newShoppingCartItem);
        }
    }

    public void removeItemFromShoppingCart(String userName, String itemName) {
        User user = getUserFromName(userName);
        if (user == null) return;

        ShoppingCart shoppingCart = getShoppingCartFromUser(user);
        if (shoppingCart == null) return;

        ShoppingCartItem shoppingCartItem = getItemInShoppingCartFromName(shoppingCart, itemName);

        if (shoppingCartItem != null) {
            shoppingCart.shoppingCartItems.remove(shoppingCartItem);
        }
    }

    public void addItemIfNotExistsYet(String itemName, double itemValue) {
        ItemInfo item = findItem(itemName);

        if (item == null) {
            ItemInfo newItem = new ItemInfo();
            newItem.itemName = itemName;
            newItem.value = itemValue;
            itens.add(newItem);
        }
    }

} 
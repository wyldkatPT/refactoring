package com.celfocus.training;

import com.celfocus.training.entity.Item;
import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Saver {

    private static final List<User> userList = new ArrayList<>();
    private static final List<ShoppingCart> shoppingCartList = new ArrayList<>();
    private static final List<Item> itemList = new ArrayList<>();


    public User createUser(String name, LocalDate dateOfBirth, boolean isAdult) {
        User user = new User(name, dateOfBirth, isAdult);
        userList.add(user);
        ShoppingCart shoppingCart = new ShoppingCart(user);
        shoppingCartList.add(shoppingCart);
        return user;
    }

    public User updateUser(String name, LocalDate dateOfBirth, boolean isAdult) {
        if (!userExists(name)) {
            return this.createUser(name, dateOfBirth, isAdult);
        }
        User user = getUserByName(name);
        user.setDateOfBirth(dateOfBirth);
        user.setAdult(isAdult);
        if (!shoppingCartExists(user)) {
            ShoppingCart shoppingCart = new ShoppingCart(user);
            shoppingCartList.add(shoppingCart);
        }
        return user;
    }

    private boolean userExists(String name) {
        return userList.stream().anyMatch(user -> name.equals(user.getName()));
    }

    private User getUserByName(String name) {
        return userList.stream().filter(user -> name.equals(user.getName())).findFirst().orElse(null);
    }

    private boolean shoppingCartExists(User user) {
        return shoppingCartList.stream().anyMatch(shoppingCart -> user == shoppingCart.getUser());
    }

    public Item findItemByName(String name) {
        return itemList.stream().filter(item -> name.equals(item.getName())).findFirst().orElse(null);
    }

    public void deleteUser(String name) {
        for (User user : userList) {
            if (name.equals(user.getName())) {
                userList.remove(user);
            }
        }
    }

    public void addItemToBasket(String userName, String itemName, int quantity) {
        if (!userExists(userName)) {
            throw new IllegalArgumentException("User doesn't exist");
        }
        User user = this.getUserByName(userName);
        if (!shoppingCartExists(user)) {
            throw new IllegalArgumentException("Shopping cart by user doesn't exist");
        }
        ShoppingCart userShoppingCart = getShoppingCartByUser(user);
        Item itemFromList = getItemFromListByName(userShoppingCart.getItems(), itemName);
        if (itemFromList != null) {
            itemFromList.setQuantity(itemFromList.getQuantity() + quantity);
            return;
        }
        Item item = findItemByName(itemName);
        if (item != null) {
            Item newItem = new Item(item.getName(), item.getValor(), quantity);
            if (user.isAdult()) {
                int yearsOfAge = Period.between(user.getDateOfBirth(), LocalDate.now()).getYears();
                newItem.setDiscount(yearsOfAge < 80 ? 0.2 : 0.1);
            }
        }
    }

    private ShoppingCart getShoppingCartByUser(User user) {
        return shoppingCartList.stream().filter(shoppingCart -> shoppingCart.getUser() == user).findFirst().orElse(null);
    }

    private Item getItemFromListByName(List<Item> itemList, String itemName) {
        return itemList.stream().filter(item -> itemName.equals(item.getName())).findFirst().orElse(null);
    }

    public void removeItemFromBasket(String userName, String itemName) {
        if (!userExists(userName)) {
            throw new IllegalArgumentException("User doesn't exist");
        }
        User user = this.getUserByName(userName);
        if (!shoppingCartExists(user)) {
            throw new IllegalArgumentException("Shopping cart by user doesn't exist");
        }
        ShoppingCart userShoppingCart = getShoppingCartByUser(user);
        removeItemFromShoppingCart(userShoppingCart.getItems(), itemName);
    }

    private void removeItemFromShoppingCart(List<Item> userShoppingCartItems, String itemName) {
        userShoppingCartItems.stream().filter(item -> itemName.equals(item.getName())).findFirst().ifPresent(userShoppingCartItems::remove);
    }

    public void createItem(String name, double valor) {
        Item item = findItemByName(name);
        if (item == null) {
            itemList.add(new Item(name, valor));
        }
    }

} 
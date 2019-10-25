package com.celfocus.training.dataAccessLayer;

import com.celfocus.training.Constants;
import com.celfocus.training.entites.ItemInfo;
import com.celfocus.training.entites.ShoppingCart;
import com.celfocus.training.entites.ShoppingCartItem;
import com.celfocus.training.entites.User;
import com.celfocus.training.exceptions.ItemNotFoundException;
import com.celfocus.training.exceptions.ShoppingCartNotFound;
import com.celfocus.training.exceptions.UserNotFoundException;
import java.util.List;

public class AccessShopping {

    private final List<User> users;
    private final List<ShoppingCart> shoppingCarts;
    private final List<ItemInfo> items;

    public AccessShopping(List<User> users, List<ShoppingCart> shoppingCarts, List<ItemInfo> items){
        this.users = users;
        this.shoppingCarts = shoppingCarts;
        this.items = items;
    }

    public void removeItems(ShoppingCart shoppingCart, String nameItem) {

        ShoppingCartItem shoppingCartItem =
            shoppingCart.getItems().stream().filter(userFound -> userFound.getItem().getName().equals(nameItem)).findFirst().get();

        if (shoppingCartItem != null) {
            shoppingCart.getItems().remove(shoppingCartItem);
        }
    }

    public void addItemToCart(String user, String nameItem, int quantity) throws UserNotFoundException, ShoppingCartNotFound, ItemNotFoundException {

        nameItem = nameItem.toLowerCase().concat("_item");
        setDiscountForOlders(user, nameItem, quantity);
    }

    public void addItemInfo(String name, double value) {

        ItemInfo itemInfo = items.stream().filter(itemInfoFound -> itemInfoFound.getName().equals(name)).findFirst().get();

        if (itemInfo == null ) {
            ItemInfo ift = new ItemInfo();
            ift.setName(name);
            ift.setValue(value);
            items.add(ift);
        }
    }

    private void setDiscountForOlders(String name, String nameItem, int quantity) throws UserNotFoundException, ShoppingCartNotFound,
        ItemNotFoundException {

        User user = users.stream().filter(userFound -> userFound.getName().equals(name)).findFirst().get();

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        ShoppingCart shoppingCart = shoppingCarts.stream().filter(x -> x.getUser().equals(user)).findFirst().get();

        if(shoppingCart == null) {
            throw new ShoppingCartNotFound("Shopping cart not found");
        }

        ShoppingCartItem shoppingCartItem = shoppingCart.getItems().stream().filter(x -> x.getItem().getName().equals(nameItem)).findFirst().get();

        if (shoppingCartItem != null) {
            shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() + quantity);
        } else {
            ItemInfo info = items.stream().filter(infoFound -> infoFound.getName().equals(nameItem)).findFirst().get();

            if(info == null) {
                throw new ItemNotFoundException("Item not found");
            }

            shoppingCartItem = new ShoppingCartItem();
            shoppingCartItem.setItem(info);
            shoppingCartItem.setQuantity(quantity);

            if(user.getAge() < Constants.olderAge) {
                return;
            }

            if (user.getAge() < Constants.eightyYears) {
                shoppingCartItem.setDiscount(Constants.discountForOlders);
            } else {
                shoppingCartItem.setDiscount(Constants.discountForEighty);
            }
        }
    }
}

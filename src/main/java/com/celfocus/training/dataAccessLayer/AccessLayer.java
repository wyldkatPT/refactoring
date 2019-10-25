package com.celfocus.training.dataAccessLayer;

import com.celfocus.training.entites.ItemInfo;
import com.celfocus.training.entites.ShoppingCart;
import com.celfocus.training.entites.User;
import java.util.ArrayList;
import java.util.List;

public final class AccessLayer {

    private final List<User> users = new ArrayList<>();
    private final List<ShoppingCart> shoppingCarts= new ArrayList<>();
    private final List<ItemInfo> items = new ArrayList<>();

    private AccessUser accessUser;
    private AccessShopping accessShopping;

    public AccessLayer() {
        accessUser = new AccessUser(users, shoppingCarts, items);
        accessShopping = new AccessShopping(users, shoppingCarts, items);
    }

}

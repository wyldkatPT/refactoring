package com.celfocus.training.user;

import com.celfocus.training.item.ItemInfo;
import com.celfocus.training.shoppingcart.ShoppingCart;

public interface UserRequestStrategy {

    String returnFrontendUser(User user);

    String returnFrontendShoppingCart(ShoppingCart shoppingCart);

    String returnFrontendItem(ItemInfo item);


}

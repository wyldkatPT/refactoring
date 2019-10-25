package com.celfocus.training.user;

import com.celfocus.training.item.ItemInfo;
import com.celfocus.training.shoppingcart.ShoppingCart;

public interface UserRequestFactory {

    default UserRequestStrategy strategy(String type) {
        if(type == "xml") {
            return new UserRequestXML();
        }
        if(type == "html") {
            return new UserRequestHTML();
        }
        return new UserRequestStrategy() {
            @Override
            public String returnFrontendUser(User user) {
                return "";
            }

            @Override
            public String returnFrontendShoppingCart(ShoppingCart shoppingCart) {
                return "";
            }

            @Override
            public String returnFrontendItem(ItemInfo item) {
                return "";
            }
        };
    }

}

package com.celfocus.training.validators;

import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.ShoppingCartItem;
import com.celfocus.training.entity.User;

import java.util.Date;

public class RepositoriesValidator {


    public boolean isLessThanEightyYearsOld(User user) {
        return new Date().getYear() - user.birthDate.getYear() < 80;
    }

    public boolean userShoppingCartItemExists(User user, ShoppingCart shoppingCart, ShoppingCartItem shoppingCartItem) {
        return user != null && shoppingCart != null && shoppingCartItem != null;
    }
}
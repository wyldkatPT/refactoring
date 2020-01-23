package com.celfocus.training.view.shoppingcart;

import com.celfocus.training.domain.ShoppingCart;
import com.celfocus.training.view.IView;

import java.util.Objects;

public class ShoppingCartViewHTML implements IView<ShoppingCart> {

    private static final String SHOPPING_CART_TEMPLATE = "<div><h1>ShoppingCart</h1><span>%s</span><span>%s</span><span>%s</span></div>";

    /**
     * Renderiza o Shopping Cart no formato HTML
     * @param shoppingCart Shopping Cart a ser renderizado
     * @return HTML com as informarções do Shopping Cart
     */
    @Override
    public String render(ShoppingCart shoppingCart) {
        Objects.requireNonNull(shoppingCart);
        return String.format(
                SHOPPING_CART_TEMPLATE,
                shoppingCart.getUser(),
                shoppingCart.getShoppingCartItemList());
    }
}


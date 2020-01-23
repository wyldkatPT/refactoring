package com.celfocus.training.view.shoppingcart;

import com.celfocus.training.domain.ShoppingCart;
import com.celfocus.training.util.StringUtils;
import com.celfocus.training.view.IView;

import java.util.Objects;

public class ShoppingCartIViewXML implements IView<ShoppingCart> {

    private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>\n";
    private static final String SHOPPING_CART_TEMPLATE = XML_HEADER + "<user>%s</user>\n<itens>%s</itens>";

    /**
     * Renderiza o Shopping Cart no formato XML
     *
     * @param shoppingCart Shopping Cart a ser renderizado
     * @return HTML com as informarções do Shopping Cart
     */
    @Override
    public String render(ShoppingCart shoppingCart) {

        Objects.requireNonNull(shoppingCart);

        return String.format(
                SHOPPING_CART_TEMPLATE,
                shoppingCart.getUser(),
                StringUtils.listToString(shoppingCart.getShoppingCartItemList(), "|"));
    }

}

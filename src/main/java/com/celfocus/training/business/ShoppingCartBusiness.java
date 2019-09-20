package com.celfocus.training.business;
import com.celfocus.training.model.ShoppingCart;
import com.celfocus.training.model.ShoppingCartItem;
import com.celfocus.training.model.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCartBusiness {

    public static final int MAX_AGE = 80;
    public static final double GREATER_THAN_80 = 0.1;
    public static final double LESS_THAN_80 = 0.2;
    private UserBusiness userBusiness;
    private ShoppingCartItemBusiness shoppingCartItemBusiness;
    private List<ShoppingCart> shoppingCarts = new ArrayList<>();

    public ShoppingCartBusiness(UserBusiness userBusiness, ShoppingCartItemBusiness shoppingCartItemBusiness){
        this.userBusiness = userBusiness;
        this.shoppingCartItemBusiness = shoppingCartItemBusiness;
    }

    public ShoppingCart findShoppingCartUser(User user ) {
        ShoppingCart shoppingCart = null;
        for (ShoppingCart s : shoppingCarts) {
            if (s.getUser().equals(user)) {
                shoppingCart = s;
            }
        }
        return shoppingCart;
    }

    public void addItemToShoppingCart(String userName, String itemName, int quantity){
        User user = userBusiness.findUser(userName);
        if(user == null) return;

        ShoppingCart shoppingCart = findShoppingCartUser(user);
        if(shoppingCart == null) return;

        ShoppingCartItem cartItem = shoppingCartItemBusiness.findShoppingCartItem(itemName);
        int incrementQuantity = cartItem.getQuantity() + quantity;
        cartItem.setQuantity(incrementQuantity);

    }

    public void removeItemFromShoppingCart(String userName, String itemName){
        User user = userBusiness.findUser(userName);

        if(user == null) return;

        ShoppingCartItem cartItem = shoppingCartItemBusiness.findShoppingCartItem(itemName);
        ShoppingCart shoppingCart = findShoppingCartUser(user);

        if(shoppingCart == null) return;

        shoppingCart.getItems().remove(cartItem);
    }

    public double getDiscount(String itemName, String userName){
        User user = userBusiness.findUser(userName);
        ShoppingCartItem cartItem = shoppingCartItemBusiness.findShoppingCartItem(itemName);

        int userAge = new Date().getYear() - user.getBirthDate().getYear();

        cartItem.setDiscount(user.isOlder() && userAge < MAX_AGE ? LESS_THAN_80 : GREATER_THAN_80);

        return cartItem.getDiscount();
    }

    public ShoppingCart saveOrUpdate(String name, Date birthDate, boolean isOlder) {
        ShoppingCart shoppingCart = null;
        User user = userBusiness.saveOrUpdate(name, birthDate, isOlder);
        if (findShoppingCartUser(user) == null) {
            shoppingCart = new ShoppingCart(user, new ArrayList<>());
            shoppingCarts.add(shoppingCart);
        }
        return shoppingCart;
    }
}

package com.celfocus.training.user;

import com.celfocus.training.item.ItemInfo;
import com.celfocus.training.item.ItemRepository;
import com.celfocus.training.shoppingcart.ShoppingCart;
import com.celfocus.training.shoppingcart.ShoppingCartItem;
import com.celfocus.training.shoppingcart.ShoppingCartRepository;


import java.util.Date;
import java.util.Optional;

public class UserService {

    private static final int MAX_AGE = 80;
    private static final double DISCOUNT_OLDER = 0.2;
    private static final double DISCOUNT_OLDER_ABOVE_MAX_AGE = 0.1;

    private UserRepository users;
    private ShoppingCartRepository shoppingCarts;
    private ItemRepository items;

    public UserService(UserRepository users, ShoppingCartRepository shoppingCarts, ItemRepository items) {
        this.users = users;
        this.shoppingCarts = shoppingCarts;
        this.items = items;
    }

    public User saveOrUpdateUser(String name, Date bd, boolean isOlder) {
        User user = new User(name, bd, isOlder);
        Optional<User> userFound = findUser(name);
        if (userFound.isPresent()) {
            user = userFound.get();
            user.birthDate = bd;
            user.isOlder = isOlder;
            Optional<ShoppingCart> shoppingCart = findShoppingCart(user);

            if (shoppingCart.isPresent()) {
                ShoppingCart newShoppingCart = new ShoppingCart(user);
                shoppingCarts.add(newShoppingCart);
            }
        } else {
            users.add(user);
            ShoppingCart s = new ShoppingCart(user);
            shoppingCarts.add(s);
        }
        return user;
    }

    public boolean userExists(String name) {
        return users.userExists(name);
    }

    public Optional<User> findUser(String name) {
        return users.findById(name);
    }

    public boolean itemExists(String name) {
        return findItem(name).isPresent();
    }

    public Optional<ItemInfo> findItem(String name) {
        return items.findById(name);
    }

    public Optional<ShoppingCart> findShoppingCart(User user) {
        return shoppingCarts.findById(user.name);
    }

    public void deleteUser(String name) {
        users.deleteById(name);
    }

    public void addItemToShoppingCart(String userName, String nameItem, int qt) {
        Optional<User> user = findUser(userName);
        if (!user.isPresent()) {
            return;
        }
        User userFound = user.get();
        findShoppingCart(userFound)
                .ifPresent(cartFound -> {
                    Optional<ShoppingCartItem> cartItem = cartFound.findShoppingCartItem(nameItem);

                    if (cartItem.isPresent()) {
                        cartItem.get().qt += qt;
                    } else {
                        Optional<ItemInfo> ifo = findItem(nameItem);

                        if (ifo.isPresent()) {
                            ShoppingCartItem s1 = new ShoppingCartItem(ifo.get(), qt);
                            if(userFound.isOlder) {
                                if (new Date().getYear() - userFound.birthDate.getYear() < MAX_AGE) {
                                    s1.discount = DISCOUNT_OLDER;
                                }
                                else {
                                    s1.discount = DISCOUNT_OLDER_ABOVE_MAX_AGE;
                                }
                            }
                            cartFound.addShoppingCartItem(s1);
                        }
                    }
                });
    }

    public void removeItemFromShoppingCart(String userName, String nameItem) {
        Optional<User> user = findUser(userName);
        if (!user.isPresent()) {
            return;
        }
        User userFound = user.get();
        findShoppingCart(userFound).ifPresent(
                shoppingCart -> shoppingCart.removeShoppingCartItem(nameItem)
        );
    }

    public void createItemIfNotExists(String name, double value) {
        if (!itemExists(name)) {
            ItemInfo itemInfo = new ItemInfo(name, value);
            items.add(itemInfo);
        }
    }


}

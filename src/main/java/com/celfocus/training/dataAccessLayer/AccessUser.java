package com.celfocus.training.dataAccessLayer;

import com.celfocus.training.exceptions.UserNotFoundException;
import com.celfocus.training.entites.ItemInfo;
import com.celfocus.training.entites.ShoppingCart;
import com.celfocus.training.entites.User;
import com.celfocus.training.util.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccessUser {

    private final List<User> users;
    private final List<ShoppingCart> shoppingCarts;
    private final List<ItemInfo> items;

    public AccessUser(List<User> users, List<ShoppingCart> shoppingCarts, List<ItemInfo> items){
        this.users = users;
        this.shoppingCarts = shoppingCarts;
        this.items = items;
    }

    public User updateUser(String name, String birthDate) throws UserNotFoundException{
        User user = users.stream().filter(x -> x.getName().equals(name)).findFirst().get();
        Date birthDateConverted = Utils.toDate(birthDate, new SimpleDateFormat("dd/mm/yyyy"));

        if(user == null){
            throw new UserNotFoundException("User not found");
        }

        user.setBirthDate(birthDateConverted);
        ShoppingCart shoppingCart = shoppingCarts.stream().filter(x -> x.getUser().equals(user)).findFirst().get();

        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
            shoppingCarts.add(shoppingCart);
        }

        return user;
    }

    public User saveUser(String name, String birthDate) {
        User user = new User();
        Date birthDateConverted = Utils.toDate(birthDate, new SimpleDateFormat("dd/mm/yyyy"));

        user.setBirthDate(birthDateConverted);
        user.setName(name);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart.setItems(new ArrayList<>());
        shoppingCarts.add(shoppingCart);

        return user;
    }

    public void deleteUser(String name) throws UserNotFoundException{
        User user = users.stream().filter(x -> x.getName().equals(name)).findFirst().get();

        if(user == null){
            throw new UserNotFoundException("User not found");
        }

        users.remove(user);
    }


} 
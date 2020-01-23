package com.celfocus.training.service;

import com.celfocus.training.model.ShoppingCart;
import com.celfocus.training.model.User;
import com.celfocus.training.repository.UserRepository;
import com.celfocus.training.util.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UserService {

    private final UserRepository userRepository;
    private final ShoppingCartService shoppingCartService;

    public UserService(UserRepository userRepository, ShoppingCartService shoppingCartService) {
        this.userRepository = userRepository;
        this.shoppingCartService = shoppingCartService;
    }

    public User saveUser(String name, String birthDate) {
        LocalDateTime formattedBirthDate = Utils.toLocalDateTIme(birthDate, DateTimeFormatter.ISO_LOCAL_DATE);
        User user = new User(name.toUpperCase(), formattedBirthDate, isOfLegalAge(formattedBirthDate));
        userRepository.createUser(user);

        ShoppingCart shoppingCart = new ShoppingCart(user, new ArrayList<>());
        shoppingCartService.createShoppingCart(shoppingCart);
        return user;
    }

    public User updateUser(String name, String birthDate) {
        User user = userRepository.getUser(name.toUpperCase());
        LocalDateTime formattedBirthDate = Utils.toLocalDateTIme(birthDate, DateTimeFormatter.ISO_LOCAL_DATE);

        if (user != null) {
            return userRepository.updateUser(name, formattedBirthDate, isOfLegalAge(formattedBirthDate));
        }

        System.out.println("This user does not exist.");
        return null;
    }

    private User getUser(String name) {
        return userRepository.getUser(name);
    }

    public void deleteUserIfExists(String name) {
        User user = userRepository.getUser(name);
        if (user != null) {
            userRepository.deleteUser(name);
        }
    }

    public boolean isOfLegalAge(LocalDateTime birthDate){
        return LocalDateTime.now().getYear() - birthDate.getYear() >= 18;
    }
}

package com.celfocus.training.controllers;

import com.celfocus.training.impl.UserImpl;
import com.celfocus.training.models.User;

import java.util.Date;

public class UserController {

    private UserImpl userImpl;

    public UserController(UserImpl userImpl) {
        this.userImpl = userImpl;
    }

    public User saveOrUpdate(String name, Date birthday, boolean isOlder) {
        return userImpl.saveOrUpdateUser(name, birthday, isOlder);
    }

    public void deleteUser(String name) {
        userImpl.deleteUserOrNot(name);
    }
}

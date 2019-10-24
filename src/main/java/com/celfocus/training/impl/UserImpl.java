package com.celfocus.training.impl;

import com.celfocus.training.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserImpl {
    private static final List<User> users = new ArrayList<>();

    public User getUser(String name) {
        User userFound = null;
        for (User user : users) {
            if (name.equals(user.getName())) {
                userFound = user;
            }
        }
        return userFound;
    }

    public boolean hasUserFound(String name) {
        return this.getUser(name) != null;
    }

    public User saveOrUpdateUser(String name, Date birthday, boolean isOlder) {
        User user;
        if (this.hasUserFound(name)) {
            user = this.getUser(name);
        } else {
            user = new User(name, birthday, isOlder);
            users.add(user);
        }
        return user;
    }

    public void deleteUserOrNot(String name) {
        if(this.hasUserFound(name)) {
            users.remove(this.getUser(name));
        }
    }
}

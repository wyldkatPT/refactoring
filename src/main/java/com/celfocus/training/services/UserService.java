package com.celfocus.training.services;

import com.celfocus.training.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserService implements CRUDService<User> {

    private List<User> users = new ArrayList<>();

    public List<User> getUsers(){
        return users;
    };

    public User find(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    };

    public boolean doesUserExist(User user) {
        return users.indexOf(user) != -1;
    }
    
    public void saveOrUpdate(User user) {

        if (!doesUserExist(user)) {
            add(user);
        } else {
            replace(user);
        }
    };

    public void add (User user) {
        users.add(user);
    }

    public void replace (User user) {
        int index = users.indexOf(user);
        users.set(index, user);
    }

    public void delete(User model) {
        users.remove(model);
    };


}

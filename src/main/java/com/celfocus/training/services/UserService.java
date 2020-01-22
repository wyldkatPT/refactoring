package com.celfocus.training.services;

import com.celfocus.training.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserService implements CRUDService<User> {

    private List<User> users = new ArrayList<>();

    public List<User> getUsers(){
        return users;
    };

    public User findByName(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    };
    
    public void saveOrUpdate(User user) {
        int index = users.indexOf(user);
        boolean doesUserExist = index != -1;

        if (!doesUserExist) {
            users.add(user);
        } else {
            users.set(index, user);
        }
    };

    public void delete(User model) {
        users.remove(model);
    };


}

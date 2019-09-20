package com.celfocus.training.repository;

import com.celfocus.training.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserRepository {

    private static final List<User> users = new ArrayList<>();

    public User saveOrUpdateUser(String userName, Date birthDate, boolean ifUserIsOlder) {
        if (userExists(userName)) {
            User user = findUserByName(userName);
            user.birthDate = birthDate;
            user.ifUserIsOlder = ifUserIsOlder;
            users.add(user);
            return user;

        }
        User user = new User();
        user.birthDate = birthDate;
        user.name = userName;
        user.ifUserIsOlder = ifUserIsOlder;
        users.add(user);
        return user;
    }

    private boolean userExists(String userName) {
        return findUserByName(userName) != null;
    }

    public void deleteUserOrNot(String userName) {
        User userFound = findUserByName(userName);
        if (userFound != null) {
            users.remove(userFound);
        }
    }

    User findUserByName(String userName) {
        User userFound = null;
        for (User user : users) {
            if (user.name.equals(userName)) {
                userFound = user;
            }
        }
        return userFound;
    }

}

package com.celfocus.training.business;
import com.celfocus.training.model.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserBusiness {

    private List<User> users = new ArrayList<>();

    public User findUser(String name) {

        User userFound = null;
        for (User user : users) {
            if (name.equals(user.getName())) {
                userFound = user;
            }
        }
        return userFound;
    }

    public boolean isExistsUser(String name) {
        return findUser(name) != null;
    }

    public User saveOrUpdate(String name, Date birthDate, boolean isOlder) {
        User user;
        if (isExistsUser(name)) {
            user = findUser(name);
        } else {
            user = new User(name, birthDate, isOlder);
            users.add(user);
        }
        return user;
    }

    public void deleteUser(String name) {
        if(isExistsUser(name)) users.remove(findUser(name));
    }
}


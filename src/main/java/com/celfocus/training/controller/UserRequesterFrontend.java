package com.celfocus.training.controller;

import com.celfocus.training.model.User;
import com.celfocus.training.service.UserService;

/**
 * User For Frontent
 */
public class UserRequesterFrontend {

    private final UserService userService;

    public UserRequesterFrontend(UserService userService) {
        this.userService = userService;
    }

    public String getFrontendUser(String type, User user) {
        if (type.equals("html")) {
            return "<div>"
                    + "<h1>User</h1>"
                    + "<span>" + user.getName() + "</span>"
                    + "<span>" + user.getBirthDate() + "</span>"
                    + "<span>" + user.isOfLegalAge() + "</span>"
                    + "</div>";
        }

        if (type.equals("xml")) {
            return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<name> " + user.getName() + "</name>"
                    + "<bd>" + user.getBirthDate() + "</bd>"
                    + "<older> " + user.isOfLegalAge() + "</older>";
        }
        return "";
    }

    public User createUser(String name, String birthDate) {
       return userService.saveUser(name, birthDate);
    }

    public User updateUser(String name, String birthDate) {
        return userService.updateUser(name, birthDate);
    }

    public void deleteUser(String arg0) {
        userService.deleteUserIfExists(arg0);
    }
}
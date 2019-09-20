package com.celfocus.training.controller;

import com.celfocus.training.business.UserBusiness;
import com.celfocus.training.model.User;

import java.util.Date;

public class UserController {

    private UserBusiness userBusiness;

    public UserController(UserBusiness userBusiness){
        this.userBusiness = userBusiness;
    }

    public User saveOrUpdate(String name, Date birthDate, boolean isOlder){

        return userBusiness.saveOrUpdate(name, birthDate, isOlder);
    }

    public void deleteUser(String name) {
        userBusiness.deleteUser(name);
    }
}

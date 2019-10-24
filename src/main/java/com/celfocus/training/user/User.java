package com.celfocus.training.user;

import java.util.Date;

public class User {
    public String name;

    public Date birthDate;

    public boolean isOlder;

    public User(String name, Date birthDate, boolean isOlder) {
        this.name = name;
        this.birthDate = birthDate;
        this.isOlder = isOlder;
    }

}

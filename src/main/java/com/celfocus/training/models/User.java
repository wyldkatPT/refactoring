package com.celfocus.training.models;

import java.util.Date;

public class User {

    private String name;

    public User(String name, Date birthday, boolean isMoreThan18) {
        this.name = name;
        this.birthday = birthday;
        this.isMoreThan18 = isMoreThan18;
    }

    private Date birthday;

    private boolean isMoreThan18;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isMoreThan18() {
        return isMoreThan18;
    }

    public void setIsMoreThan18(boolean isMoreThan18) {
        this.isMoreThan18 = isMoreThan18;
    }
}

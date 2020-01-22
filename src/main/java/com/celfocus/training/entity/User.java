package com.celfocus.training.entity;

import java.util.Date;

public class User {

    private String name;
    private Date birthday;
    private boolean notMinor;

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

    public boolean isNotMinor() {
        return notMinor;
    }

    public void setNotMinor(boolean notMinor) {
        this.notMinor = notMinor;
    }

    public User(String name, Date birthday, boolean notMinor) {
        this.name = name;
        this.birthday = birthday;
        this.notMinor = notMinor;
    }
}

package com.celfocus.training.models;

import java.util.Date;

public class User {

    private String name;
    private Date birthDay;
    private boolean isOlder;

    public User(String name, Date birthDay, boolean isOlder){
        this.name = name;
        this.birthDay = birthDay;
        this.isOlder = isOlder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public boolean getIsOlder() {
        return isOlder;
    }

    public void setOlder(boolean older) {
        isOlder = older;
    }
}

package com.celfocus.training;

import java.util.Date;

public class User {

    private String userName;
    private Date birthDate;
    public boolean isAdult;

    public User() {
        this.userName = userName;
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

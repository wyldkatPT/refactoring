package com.celfocus.training.user;

import java.util.Date;

public class User {

    private String username;
    private Date birthDate;
    public boolean isAdult;

    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

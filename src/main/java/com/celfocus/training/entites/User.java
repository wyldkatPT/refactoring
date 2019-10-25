package com.celfocus.training.entites;

import java.util.Date;

public class User {

    private String name;

    private Date date;

    private Date birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        return date.getYear() - birthDate.getYear();
    }
}

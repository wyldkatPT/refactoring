package com.celfocus.training.model;
import java.util.Date;

public class User {

    private String name;

    private Date birthDate;

    private boolean isOlder;

    public User(String name, Date birthDate, boolean isOlder){
        this.name = name;
        this.birthDate = birthDate;
        this.isOlder = isOlder;
    }

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

    public boolean isOlder() {
        return isOlder;
    }

    public void setOlder(boolean older) {
        isOlder = older;
    }
}

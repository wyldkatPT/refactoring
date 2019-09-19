package com.celfocus.training;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {


    private String nameOfUser; // nome

    private Date birthdayDate; // data de nascimento


    public User(String nameOfUser, Date birthdayDate) {
        this.nameOfUser = nameOfUser;
        this.birthdayDate = birthdayDate;
    }

    public String getNameOfUser() {
        return nameOfUser;
    }

    public int getAge(){
        return new Date().getYear() - this.birthdayDate.getYear();
    }

    public void setNameOfUser(String nameOfUser) {
        this.nameOfUser = nameOfUser;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }



    @Override
    public String toString() {
        return "User{" +
                "nameOfUser='" + nameOfUser + '\'' +
                ", birthdayDate=" + birthdayDate +
                '}';
    }
}

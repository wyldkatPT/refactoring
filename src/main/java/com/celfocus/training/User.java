package com.celfocus.training;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {


    private String nameOfUser; // nome

    private Date birthdayDate; // data de nascimento

    private boolean isAgeOver18; // se usuário é maior de idade

    public User(String nameOfUser, Date birthdayDate, boolean isAgeOver18) {
        this.nameOfUser = nameOfUser;
        this.birthdayDate = birthdayDate;
        this.isAgeOver18 = isAgeOver18;
    }

    public String getNameOfUser() {
        return nameOfUser;
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

    public boolean getIsAgeOver18() {
        return isAgeOver18;
    }

    public void setIsAgeOver18(boolean isAgeOver18) {
        this.isAgeOver18 = isAgeOver18;
    }

    @Override
    public String toString() {
        return "User{" +
                "nameOfUser='" + nameOfUser + '\'' +
                ", birthdayDate=" + birthdayDate +
                ", isAgeOver18=" + isAgeOver18 +
                '}';
    }
}

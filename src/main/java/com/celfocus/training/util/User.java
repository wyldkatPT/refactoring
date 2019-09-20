package com.celfocus.training.util;

import java.util.Date;

public class User {
    public String nameOfUser;

    public Date dateOfBirth;

    public boolean isLegalAge;

    public User(String nameOfUser, Date dateOfBirth, boolean isLegalAge) {
        this.nameOfUser = nameOfUser;
        this.dateOfBirth = dateOfBirth;
        this.isLegalAge = isLegalAge;
    }

    public User() {
    }

    public String getNameOfUser() {
        return nameOfUser;
    }

    public void setNameOfUser(String nameOfUser) {
        this.nameOfUser = nameOfUser;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isLegalAge() {
        return isLegalAge;
    }

    public void setLegalAge(boolean legalAge) {
        isLegalAge = legalAge;
    }
}

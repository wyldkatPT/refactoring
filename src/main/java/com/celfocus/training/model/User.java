package com.celfocus.training.model;

import java.time.LocalDateTime;

public class User {

    private String name;

    private LocalDateTime birthDate;

    private boolean isOfLegalAge;

    public User(String name, LocalDateTime birthDate, boolean isOfLegalAge) {
        this.name = name;
        this.birthDate = birthDate;
        this.isOfLegalAge = isOfLegalAge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isOfLegalAge() {
        return isOfLegalAge;
    }

    public void setOfLegalAge(boolean ofLegalAge) {
        isOfLegalAge = ofLegalAge;
    }

    public boolean hasLessThan80years() {
        return LocalDateTime.now().getYear() - birthDate.getYear() < 80;
    }
}
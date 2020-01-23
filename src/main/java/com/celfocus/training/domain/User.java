package com.celfocus.training.domain;

import com.celfocus.training.exceptions.user.BirthDateNotFoundException;
import com.celfocus.training.exceptions.user.IllegalBirthDateException;
import com.celfocus.training.util.DateUtils;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Objects;

public class User {

    private String username;

    private LocalDate birthDate;

    private boolean hasLegalAge;

    public User(String username, LocalDate birthDate, int legalAge){
        this.setUsername(username);
        this.setBirthDate(birthDate);
        this.setHasLegalAge(legalAge);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBirthDate(LocalDate birthDate) {
        if(birthDate.isAfter(DateUtils.getNow())) {
            throw new IllegalBirthDateException("BirthDate should be before today");
        }
        this.birthDate = birthDate;

    }

    public String getUsername(){
        return this.username;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public boolean hasLegalAge() {
        return this.hasLegalAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString(){
        return this.getUsername();
    }

    public void setHasLegalAge(int legalAge) {
        if(birthDate == null) {
            throw new BirthDateNotFoundException("To calculate legal age it's necessary user have birthDate!");
        }

        LocalDate nowDate = DateUtils.getNow();

        Period nowPeriod = Period.of(nowDate.getYear(), nowDate.getMonthValue(), nowDate.getDayOfMonth());

        Period birthDatePeriod = Period.of(this.birthDate.getYear(), this.birthDate.getMonthValue(), this.birthDate.getDayOfMonth());

        this.hasLegalAge = nowPeriod.minus(birthDatePeriod).getYears() >= legalAge;
    }

}

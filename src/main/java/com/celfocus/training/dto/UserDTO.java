package com.celfocus.training.dto;

public class UserDTO {

    private String username;

    private String birthDate;

    private String hasLegalAge;

    public UserDTO(String username, String birthDate, String hasLegalAge){
        this.setUsername(username);
        this.setBirthDate(birthDate);
        this.setHasLegalAge(hasLegalAge);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setHasLegalAge(String hasLegalAge) {
        this.hasLegalAge = hasLegalAge;
    }

    public String getUsername(){
        return this.username;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String hasLegalAge() {
        return hasLegalAge;
    }
}

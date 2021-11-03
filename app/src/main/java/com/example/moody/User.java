package com.example.moody;

public class User {
    //field
    private String picture;//need change
    private String firstname;
    private String lastname;
    private String birthday;
    private String username;
    private String email;
    private String password;
    private String race;
    private String occupation;

    //function
    public User(String picture, String firstname,String lastname,String birthday,String username,String email,String password,String race,String occupation){
        this.picture=picture;
        this.firstname=firstname;
        this.lastname=lastname;
        this.birthday=birthday;
        this.username=username;
        this.email=email;
        this.password=password;
        this.race=race;
        this.occupation=occupation;
    }
    //getters and setters


    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture){
        this.picture=picture;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }
}

package com.ita.provapp.server.common.json;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class User {

    private Integer userID;
    @NotNull(message = "Username can't be empty")
    private String username;
    @NotNull(message = "Name can't be empty")
    private String name;
    @NotNull(message = "Surname can't be empty")
    private String surname;
    //private Date dateOfBirth;
    @Email
    private String email;

    public User() {

    }

    public User(Integer userID, String username, String name, String surname, String email) {
        this(username,name,surname,email);
        this.userID = userID;
    }

    public User(String username, String name, String surname, String email) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    //public Date getDateOfBirth() {
    //    return dateOfBirth;
    //}

    public String getEmail() {
        return email;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}

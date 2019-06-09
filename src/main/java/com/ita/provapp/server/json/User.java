package com.ita.provapp.server.json;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class User {

    private Integer userID;
    @NotNull
    private String username;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    //private Date dateOfBirth;
    @NotNull
    private String email;

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

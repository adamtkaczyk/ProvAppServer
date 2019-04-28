package com.ita.provapp.server;

import java.util.Date;

public class User {

    private String username;
    private String name;
    private String surname;
    //private Date dateOfBirth;
    private String email;

    public User(String username, String name, String surname, /*Date dateOfBirth,*/ String email) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        //this.dateOfBirth = dateOfBirth;
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
}

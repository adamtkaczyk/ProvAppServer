package com.ita.provapp.server.common.json;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {

    private Integer userID;
    @NotEmpty(message = "Username can't be empty")
    private String username;
    @NotEmpty(message = "Name can't be empty")
    private String name;
    @NotEmpty(message = "Surname can't be empty")
    private String surname;
    //private Date dateOfBirth;
    @Email(message = "Incorrect email address")
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

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof User)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        User user = (User) object;

        // Compare the data members and return accordingly
        return username.equals(user.username) && name.equals(user.name) && surname.equals(user.surname) && email.equals(user.email);
    }
}

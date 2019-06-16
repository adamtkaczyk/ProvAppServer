package com.ita.provapp.server.common.json;

import javax.validation.constraints.NotNull;

public class NewUser extends User {

    @NotNull(message = "Password can not be empty")
    private String password;

    public NewUser() {

    }

    public NewUser(String username, String name, String surname, /*Date dateOfBirth,*/ String email, String password) {
        super(username, name, surname, email);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

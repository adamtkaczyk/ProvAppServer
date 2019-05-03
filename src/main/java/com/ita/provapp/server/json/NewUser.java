package com.ita.provapp.server.json;

public class NewUser extends User {

    private String password;

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

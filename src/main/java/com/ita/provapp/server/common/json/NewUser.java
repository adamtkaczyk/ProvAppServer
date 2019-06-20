package com.ita.provapp.server.common.json;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewUser extends User {

    @NotEmpty(message = "Password can not be empty")
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

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof NewUser)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        NewUser user = (NewUser) object;

        // Compare the data members and return accordingly
        return super.equals(object) && password.equals(user.password);
    }
}

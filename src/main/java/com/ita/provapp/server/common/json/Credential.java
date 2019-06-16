package com.ita.provapp.server.common.json;

public class Credential {
    private String user;
    private String password;

    public Credential(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public Credential() {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

        if (!(object instanceof Credential)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Credential credential = (Credential) object;

        // Compare the data members and return accordingly
        return user.equals(credential.user) && password.equals(credential.getPassword());
    }
}

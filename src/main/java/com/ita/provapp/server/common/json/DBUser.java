package com.ita.provapp.server.common.json;

public class DBUser {
    private User user;
    private String passwordHash;

    public DBUser(User user, String passwordHash) {
        this.passwordHash = passwordHash;
        this.user = user;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

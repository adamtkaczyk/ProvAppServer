package com.ita.provapp.server;

import java.util.ArrayList;

public class AccountManagerTemporary extends AccountsManager {
    private ArrayList<User> users = new ArrayList<>();


    public void addUser(NewUser user) {
        users.add(new User(user.getUsername(),user.getName(),user.getSurname(),user.getEmail()));
    }

    public User getUser(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().get();
    }

    public boolean userExists(String username) {
        return users.stream().anyMatch(user -> user.getUsername().equals(username));
    }
}

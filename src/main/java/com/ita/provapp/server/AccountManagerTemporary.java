package com.ita.provapp.server;

import com.ita.provapp.server.json.NewUser;
import com.ita.provapp.server.json.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

class DBUser {
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

public class AccountManagerTemporary extends AccountsManager {
    private ArrayList<DBUser> users = new ArrayList<>();
    private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);


    @Override
    public void addUser(NewUser user) throws EntityExistsException {
        if(userExists(user.getUsername())) {
            logger.warn("Cannot add new user. User: '" + user.getUsername() + "' exists");
            throw new EntityExistsException("Cannot add new user. User: '" + user.getUsername() + "' already exists");
        } else {
            users.add(new DBUser(
                    new User(user.getUsername(),user.getName(),user.getSurname(),user.getEmail()),
                    AccountsManager.generatePasswordHash(user.getPassword())));
        }
    }

    @Override
    protected User getUserByPassword(String username, String password) throws EntityNotFoundException, PasswordIncorrectException {
        DBUser user = findUser(username);
        String passwordHash = generatePasswordHash(password);

        if (passwordHash.equals(user.getPasswordHash())) {
            return user.getUser();
        } else {
            logger.warn("Incorrect password for user: " + user.getUser().getUsername());
            throw new PasswordIncorrectException();
        }
    }

    @Override
    public User getUserByToken(String username, String token) throws EntityNotFoundException, AuthTokenIncorrectException {
        if(token.isEmpty())
            throw new AuthTokenIncorrectException();
        return findUser(username).getUser();
    }

    @Override
    protected void saveToken(String token) {

    }

    public boolean userExists(String username) {
        return users.stream().anyMatch(user -> user.getUser().getUsername().equals(username));
    }

    public DBUser getUser(String username) {
        return users.stream().filter(user -> user.getUser().getUsername().equals(username)).findFirst().get();
    }


    private DBUser findUser(String username) throws EntityNotFoundException {
        if(userExists(username)) {
            logger.debug("Found user: " + username);
            return getUser(username);
        } else {
            logger.warn("User: " + username + " not exists.");
            throw new EntityNotFoundException("User: " + username + " not exists.");
        }
    }
}

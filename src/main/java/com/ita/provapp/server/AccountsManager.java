package com.ita.provapp.server;

import com.ita.provapp.server.json.LoginUser;
import com.ita.provapp.server.json.Credential;
import com.ita.provapp.server.json.NewUser;
import com.ita.provapp.server.json.User;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public abstract class AccountsManager {

    private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    public abstract void addUser(NewUser user) throws EntityExistsException;
    public abstract User getUserByToken(String username, String password) throws EntityNotFoundException, AuthTokenIncorrectException;

    protected abstract void saveToken(String token);
    protected abstract User getUserByPassword(String username, String password) throws EntityNotFoundException, PasswordIncorrectException;


    public LoginUser authenticate(Credential credential) throws EntityNotFoundException, PasswordIncorrectException {
        User user = getUserByPassword(credential.getUser(),credential.getPassword());
        String token = AccountsManager.generateAuthToken(credential.getUser());
        saveToken(token);

        return new LoginUser(token, user);
    }

    public static String generateAuthToken(String username) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        String key = UUID.randomUUID().toString().toUpperCase() + "|" + "provapp" + "|" + username + "|" + dateFormat.format(date);
        logger.debug("Create key for new token: " + key);

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("jasypt");
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
        String token = encryptor.encrypt(key);
        logger.info("Create new token: " + token);

        return token;
    }

    public static String generatePasswordHash(String password) {
        return password;
    }
}

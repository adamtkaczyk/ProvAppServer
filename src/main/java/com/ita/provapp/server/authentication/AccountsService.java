package com.ita.provapp.server.authentication;

import com.ita.provapp.server.common.exceptions.AuthTokenIncorrectException;
import com.ita.provapp.server.common.exceptions.EntityExistsException;
import com.ita.provapp.server.common.exceptions.EntityNotFoundException;
import com.ita.provapp.server.common.exceptions.PasswordIncorrectException;
import com.ita.provapp.server.common.json.LoginUser;
import com.ita.provapp.server.common.json.Credential;
import com.ita.provapp.server.common.json.NewUser;
import com.ita.provapp.server.common.json.User;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public abstract class AccountsService {

    private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    public abstract Integer addUser(NewUser user) throws EntityExistsException;
    public abstract User getUserByToken(String username, String password) throws EntityNotFoundException, AuthTokenIncorrectException;

    protected abstract void saveToken(String token);
    protected abstract User getUserByPassword(String username, String password) throws EntityNotFoundException, PasswordIncorrectException;

    protected abstract User getUserByToken(Integer userId, String token) throws EntityNotFoundException, AuthTokenIncorrectException;

    public LoginUser authenticate(Credential credential) throws EntityNotFoundException, PasswordIncorrectException {
        User user = getUserByPassword(credential.getUser(),credential.getPassword());
        String token = AccountsService.generateAuthToken(credential.getUser());
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

    public boolean logout(String authtoken) {
        return true;
    }

    public static String generatePasswordHash(String password) {
        return password;
    }
}

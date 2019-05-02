package com.ita.provapp.server;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public abstract class AccountsManager{

    private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    public abstract void addUser(NewUser user);
    public abstract User getUser(String username);
    public abstract boolean userExists(String username);

    public static String generateAuthotoken(String username) {
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
}

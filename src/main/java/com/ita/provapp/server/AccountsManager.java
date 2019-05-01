package com.ita.provapp.server;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public abstract class AccountsManager{

    public abstract void addUser(NewUser user);
    public abstract User getUser(String username);
    public abstract boolean userExists(String username);

    public static String generateAuthotoken(String username) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        String key = UUID.randomUUID().toString().toUpperCase() + "|" + "provapp" + "|" + username + "|" + dateFormat.format(date);
        System.out.println("Key: " + key);

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("jasypt");
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
        String token = encryptor.encrypt(key);
        System.out.println("Token: " + token);

        return token;
    }
}

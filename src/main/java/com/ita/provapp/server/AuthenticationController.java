package com.ita.provapp.server;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class AuthenticationController {

    @RequestMapping(value = "/authtoken", method = RequestMethod.POST)
    public ResponseEntity<Authentication> authentication(@RequestBody Credential credential) {
        //TODO: check if password (password hash) is correct
        if(credential.getPassword().equals("admin1")) {

            Authentication auth = new Authentication(generateAuthotoken(credential.getUser()), findUser(credential.getUser()));

            return new ResponseEntity<>(auth, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody NewUser user) {
        //TODO: check if user exists
        //TODO: if user not exist save user in database
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable String username) {
        //TODO: check if user find if not return error code
        User user = findUser(username);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    private User findUser(String username) {
        //TODO: Find user in database
        return new User(username, "Adam", "Tkaczyk", /*new Date("26-08-1990"),*/ "adamtkaczyk90@gmail.com");
    }

    private String generateAuthotoken(String username) {
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

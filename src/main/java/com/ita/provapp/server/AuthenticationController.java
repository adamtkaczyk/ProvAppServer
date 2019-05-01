package com.ita.provapp.server;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthenticationController {

    private AccountsManager acccountsManager = new AccountManagerTest();

    @RequestMapping(value = "/authtoken", method = RequestMethod.POST)
    public ResponseEntity<Authentication> authentication(@RequestBody Credential credential) {
        if(acccountsManager.userExists(credential.getUser())) {
            //TODO: check if password (password hash) is correct
            if (credential.getPassword().equals("admin1")) {

                Authentication auth = new Authentication(AccountsManager.generateAuthotoken(credential.getUser()), findUser(credential.getUser()));
                return new ResponseEntity<>(auth, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody NewUser user) {
        if(acccountsManager.userExists(user.getUsername())) {
            System.out.println("Cannot add new user. User: '" + user.getUsername() + "' exists");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            System.out.println("Add new user: " + user.getUsername());
            acccountsManager.addUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable String username) {
        if(acccountsManager.userExists(username)) {
            //TODO: check if user find if not return error code
            User user = findUser(username);

            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    private User findUser(String username) {
        return acccountsManager.getUser(username);
    }
}

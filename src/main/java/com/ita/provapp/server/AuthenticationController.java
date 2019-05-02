package com.ita.provapp.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthenticationController {

    private AccountsManager acccountsManager = new AccountManagerTemporary();
    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @RequestMapping(value = "/authtoken", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Authentication authentication(@RequestBody Credential credential) throws EntityNotFoundException, PasswordIncorrectException {
        User user = findUser(credential.getUser());
        //TODO: check if password (password hash) is correct
        if (credential.getPassword().equals("admin1")) {
            return new Authentication(AccountsManager.generateAuthotoken(credential.getUser()), user);
        } else {
            logger.warn("Incorrect password for user: " + credential.getUser());
            throw new PasswordIncorrectException();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody NewUser user) throws EntityExistsException{
        if(acccountsManager.userExists(user.getUsername())) {
            logger.warn("Cannot add new user. User: '" + user.getUsername() + "' exists");
            throw new EntityExistsException("Cannot add new user. User: '" + user.getUsername() + "' already exists");
        } else {
            logger.info("Add new user: " + user.getUsername());
            acccountsManager.addUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User getUser(@PathVariable String username) throws EntityNotFoundException {
            return findUser(username);
    }

    private User findUser(String username) throws EntityNotFoundException {
        if(acccountsManager.userExists(username)) {
            logger.debug("Found user: " + username);
            return acccountsManager.getUser(username);
        } else {
            logger.warn("User: " + username + " not exists.");
            throw new EntityNotFoundException("User: " + username + " not exists.");
        }
    }
}

class EntityNotFoundException extends Throwable {
    public EntityNotFoundException(String message) {
        super(message);
    }
}

class EntityExistsException extends  Throwable {
    public EntityExistsException(String message) {
        super(message);
    }
}

class PasswordIncorrectException extends Throwable {
    public PasswordIncorrectException() {
        super("Password incorrect");
    }
}

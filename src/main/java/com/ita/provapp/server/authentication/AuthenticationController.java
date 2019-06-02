package com.ita.provapp.server.authentication;

import com.ita.provapp.server.json.*;
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
    public LoginUser authentication(@RequestBody Credential credential) throws EntityNotFoundException, PasswordIncorrectException {
        //TODO: set location header
        logger.info("LoginUser request, user=[" + credential.getUser() + "]");
        return acccountsManager.authenticate(credential);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody NewUser user) throws EntityExistsException {
        //TODO: set location header
        logger.info("Add new user: " + user.getUsername());
        acccountsManager.addUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User getUser(@PathVariable String username, @RequestHeader("Authorization") String authToken) throws AuthTokenIncorrectException, EntityNotFoundException {
        logger.info("Get user request. Username: " + username + " , token: " + authToken);
        return acccountsManager.getUserByToken(username, authToken);
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

class AuthTokenIncorrectException extends Throwable {
    public AuthTokenIncorrectException() {
        super("AuthToken incorrect");
    }
}

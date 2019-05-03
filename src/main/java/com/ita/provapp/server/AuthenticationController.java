package com.ita.provapp.server;

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
    public Authentication authentication(@RequestBody Credential credential) throws EntityNotFoundException, PasswordIncorrectException {
        return acccountsManager.authenticate(credential);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody NewUser user) throws EntityExistsException {
        logger.info("Add new user: " + user.getUsername());
        acccountsManager.addUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User getUser(@PathVariable String username, @RequestBody AuthToken authToken) throws AuthTokenIncorrectException, EntityNotFoundException {
        return acccountsManager.getUserByToken(username, authToken.getToken());
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

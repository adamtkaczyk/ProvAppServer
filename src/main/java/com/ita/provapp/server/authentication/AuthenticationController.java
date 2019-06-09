package com.ita.provapp.server.authentication;

import com.ita.provapp.server.exceptions.EntityExistsException;
import com.ita.provapp.server.exceptions.EntityNotFoundException;
import com.ita.provapp.server.json.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class AuthenticationController {

    private AccountsManager acccountsManager = new AccountManagerTemporary();
    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @RequestMapping(value = "/authtoken", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public LoginUser authentication(@RequestBody Credential credential) throws EntityNotFoundException, PasswordIncorrectException {
        logger.info("POST /user/authtoken. LoginUser request, user=[" + credential.getUser() + "]");
        return acccountsManager.authenticate(credential);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addUser(@Valid @RequestBody NewUser user) throws EntityExistsException {
        logger.info("POST /user. Add new user request: " + user.getUsername());
        Integer userID = acccountsManager.addUser(user);
        String location = String.format("/user/%d",userID);
        logger.info(String.format("User add successfully in location=[%s]",location));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Location",location);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /*@RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User getUser(@PathVariable String username, @RequestHeader("Authorization") String authToken) throws AuthTokenIncorrectException, EntityNotFoundException {
        logger.info("Get user request. Username: " + username + " , token: " + authToken);
        return acccountsManager.getUserByToken(username, authToken);
    }*/

    @RequestMapping(value = "/{userID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User getUser(@PathVariable Integer userID, @RequestHeader("Authorization") String authToken) throws AuthTokenIncorrectException, EntityNotFoundException {
        logger.info(String.format("GET /user/%d. Get user request. UserID: [%s] , token: [%s]", userID, userID, authToken));
        return acccountsManager.getUserByToken(userID, authToken);
    }

    public void setAcccountsManager(AccountsManager acccountsManager) {
        this.acccountsManager = acccountsManager;
    }
}


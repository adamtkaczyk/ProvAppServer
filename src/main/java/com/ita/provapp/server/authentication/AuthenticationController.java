package com.ita.provapp.server.authentication;

import com.ita.provapp.server.common.exceptions.AuthTokenIncorrectException;
import com.ita.provapp.server.common.exceptions.EntityExistsException;
import com.ita.provapp.server.common.exceptions.EntityNotFoundException;
import com.ita.provapp.server.common.exceptions.PasswordIncorrectException;
import com.ita.provapp.server.common.json.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@EnableWebMvc
public class AuthenticationController {

    @Autowired
    private AccountsService accountsService;

    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @RequestMapping(value = "/authtokens", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<LoginUser> authentication(@RequestBody Credential credential) throws EntityNotFoundException, PasswordIncorrectException {
        System.out.println("Credential: " + credential.getUser() + " , password: " + credential.getPassword());
        logger.info(String.format("POST /users/authtokens. LoginUser request, user=[%s]",credential.getUser()));
        LoginUser user = accountsService.authenticate(credential);

        String location = String.format("/user/%s",user.getUser().getUsername());
        logger.info(String.format("User log in location=[%s]",location));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Location",location);
        return new ResponseEntity<>(user,headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addUser(@Valid @RequestBody NewUser user) throws EntityExistsException {
        logger.info(String.format("POST /users. Add new user request: [%s]", user.getUsername()));
        Integer userID = accountsService.addUser(user);
        String location = String.format("/user/%s",user.getUsername());
        logger.info(String.format("User add successfully in location=[%s]",location));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Location",location);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User getUser(@PathVariable String username, @RequestHeader("Authorization") String authToken) throws AuthTokenIncorrectException, EntityNotFoundException {
        logger.info(String.format("GET /users/%s. Get user request. Username: [%s] , token: [%s]",username, username, authToken));
        return accountsService.getUserByToken(username, authToken);
    }

    /*@RequestMapping(value = "/{userID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User getUser(@PathVariable Integer userID, @RequestHeader("Authorization") String authToken) throws AuthTokenIncorrectException, EntityNotFoundException {
        logger.info(String.format("GET /user/%d. Get user request. UserID: [%s] , token: [%s]", userID, userID, authToken));
        return accountsService.getUserByToken(userID, authToken);
    }*/

    public void setAccountsService(AccountsService accountsService) {
        this.accountsService = accountsService;
    }
}


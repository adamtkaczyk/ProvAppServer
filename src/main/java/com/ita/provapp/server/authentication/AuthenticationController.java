package com.ita.provapp.server.authentication;

import com.ita.provapp.server.provappcommon.exceptions.AuthTokenIncorrectException;
import com.ita.provapp.server.provappcommon.exceptions.EntityExistsException;
import com.ita.provapp.server.provappcommon.exceptions.EntityNotFoundException;
import com.ita.provapp.server.provappcommon.exceptions.PasswordIncorrectException;
import com.ita.provapp.server.provappcommon.json.*;
import com.ita.provapp.server.AppConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return (httpResponse.getStatusCode().series() == CLIENT_ERROR ||
                httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        logger.error("ERROR !!!!!!!!!!!!!!!!!!!!!");
    }
}

@RestController
@RequestMapping("/users")
public class AuthenticationController {

    private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private AppConfiguration conf = AppConfiguration.getInstance();
    private final String ENDPOINT_URL = conf.getValue("provapp.authenticator.host");// "http://provappauth:8081/users";

    /*@RequestMapping(value = "/authtokens", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<LoginUser> authentication(@Valid @RequestBody Credential credential) throws EntityNotFoundException, PasswordIncorrectException {

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(ENDPOINT_URL, credential, LoginUser.class);
    }*/

    /*@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addUser(@Valid @RequestBody NewUser user) throws EntityExistsException {
        logger.info(String.format("POST /users. Add new user request: [%s]", user.getUsername()));
        Integer userID = accountsService.addUser(user);
        String location = String.format("/users/%s",user.getUsername());
        logger.info(String.format("User add successfully in location=[%s]",location));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Location",location);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }*/

    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<User> getUser(@PathVariable String username, @RequestHeader("Authorization") String authToken) throws AuthTokenIncorrectException, EntityNotFoundException {
        logger.info(String.format("GET /users/%s. Get user request. Username: [%s] , token: [%s]",username, username, authToken));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        RestTemplate restTemplate = new RestTemplate();

        logger.info(String.format("Send REST request GET %s/%s", ENDPOINT_URL, username));
        return restTemplate.exchange(String.format("%s/%s", ENDPOINT_URL, username), HttpMethod.GET, entity, User.class);
    }

    /*@RequestMapping(value = "/{userID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User getUser(@PathVariable Integer userID, @RequestHeader("Authorization") String authToken) throws AuthTokenIncorrectException, EntityNotFoundException {
        logger.info(String.format("GET /user/%d. Get user request. UserID: [%s] , token: [%s]", userID, userID, authToken));
        return accountsService.getUserByToken(userID, authToken);
    }*/

    /*@RequestMapping(value =  "authtokens", method = RequestMethod.DELETE)
    public ResponseEntity logout(@RequestHeader("Authorization") String authToken) {
        boolean isLogout = accountsService.logout(authToken);
        if(isLogout) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }*/
}


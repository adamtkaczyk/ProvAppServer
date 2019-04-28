package com.ita.provapp.server;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/user/authtoken")
public class AuthenticationController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Authentication> authentication(@RequestBody Credential credential) {
        if(credential.getPassword().equals("admin1")) {
            Authentication auth = new Authentication("9873",
                    new User(credential.getUser(), "Adam", "Tkaczyk", /*new Date("26-08-1990"),*/ "adamtkaczyk90@gmail.com"));

            return new ResponseEntity<>(auth, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}

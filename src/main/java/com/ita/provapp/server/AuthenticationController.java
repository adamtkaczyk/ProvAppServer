package com.ita.provapp.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/authtoken")
public class AuthenticationController {

    @RequestMapping(method = RequestMethod.GET)
    public Authentication authentication() {
        return new Authentication("9876");
    }
}

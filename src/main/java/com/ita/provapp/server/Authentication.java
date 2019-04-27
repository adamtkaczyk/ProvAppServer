package com.ita.provapp.server;

public class Authentication {

    public Authentication(String authtoken) {
        this.authtoken = authtoken;
    }

    private final String authtoken;

    public String getAuthtoken() {
        return authtoken;
    }
}

package com.ita.provapp.server.authentication;

public class AuthTokenIncorrectException extends Throwable {
    public AuthTokenIncorrectException() {
        super("AuthToken incorrect");
    }
}

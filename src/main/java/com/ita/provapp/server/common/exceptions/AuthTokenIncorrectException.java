package com.ita.provapp.server.common.exceptions;

public class AuthTokenIncorrectException extends Throwable {
    public AuthTokenIncorrectException() {
        super("AuthToken incorrect");
    }
}

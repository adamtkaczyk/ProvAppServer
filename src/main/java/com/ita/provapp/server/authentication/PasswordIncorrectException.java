package com.ita.provapp.server.authentication;

public class PasswordIncorrectException extends Throwable {
    public PasswordIncorrectException() {
        super("Password incorrect");
    }
}

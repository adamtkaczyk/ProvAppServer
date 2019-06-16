package com.ita.provapp.server.common.exceptions;

public class PasswordIncorrectException extends Throwable {
    public PasswordIncorrectException() {
        super("Password incorrect");
    }
}

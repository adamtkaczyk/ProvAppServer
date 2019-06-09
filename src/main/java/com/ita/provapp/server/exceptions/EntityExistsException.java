package com.ita.provapp.server.exceptions;

public class EntityExistsException extends  Throwable {
    public EntityExistsException(String message) {
        super(message);
    }
}

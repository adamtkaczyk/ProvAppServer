package com.ita.provapp.server.common.exceptions;

public class EntityExistsException extends  Throwable {
    public EntityExistsException(String message) {
        super(message);
    }
}

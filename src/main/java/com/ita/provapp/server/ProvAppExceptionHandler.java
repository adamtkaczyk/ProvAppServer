package com.ita.provapp.server;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ProvAppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    protected ErrorMessage handlerEntityNotFoundException(EntityNotFoundException ex) {
        return new ErrorMessage(ex.getMessage());
    }

    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    protected ErrorMessage handlerEntityExistsException(EntityExistsException ex) {
        return new ErrorMessage(ex.getMessage());
    }

    @ExceptionHandler(PasswordIncorrectException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    protected ErrorMessage handlerPasswordIncorrectException(PasswordIncorrectException ex) {
        return new ErrorMessage(ex.getMessage());
    }
}

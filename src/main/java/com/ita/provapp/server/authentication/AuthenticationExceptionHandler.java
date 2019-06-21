package com.ita.provapp.server.authentication;

import com.ita.provapp.server.common.exceptions.AuthTokenIncorrectException;
import com.ita.provapp.server.common.exceptions.EntityExistsException;
import com.ita.provapp.server.common.exceptions.EntityNotFoundException;
import com.ita.provapp.server.common.exceptions.PasswordIncorrectException;
import com.ita.provapp.server.common.json.ErrorMessage;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class AuthenticationExceptionHandler extends com.ita.provapp.server.common.ExceptionHandler {
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
        return new ErrorMessage(ex.getMessage(), 409);
    }

    @ExceptionHandler(PasswordIncorrectException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    protected ErrorMessage handlerPasswordIncorrectException(PasswordIncorrectException ex) {
        return new ErrorMessage("Incorrect username or password");
    }

    @ExceptionHandler(AuthTokenIncorrectException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    protected ErrorMessage handlerAuthTokenIncorrectException(AuthTokenIncorrectException ex) {
        return new ErrorMessage(ex.getMessage());
    }
}

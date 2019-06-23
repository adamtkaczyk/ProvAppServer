package com.ita.provapp.server.authentication;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ita.provapp.server.provappcommon.exceptions.AuthTokenIncorrectException;
import com.ita.provapp.server.provappcommon.exceptions.EntityExistsException;
import com.ita.provapp.server.provappcommon.exceptions.EntityNotFoundException;
import com.ita.provapp.server.provappcommon.exceptions.PasswordIncorrectException;
import com.ita.provapp.server.provappcommon.json.ErrorMessage;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class AuthenticationExceptionHandler extends com.ita.provapp.server.provappcommon.ExceptionHandler {
    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    protected ErrorMessage handlerHttpClientErrorException(HttpClientErrorException ex) {
        ObjectMapper mapper = new ObjectMapper();
        try {

            return mapper.readValue(ex.getResponseBodyAsString(), ErrorMessage.class);
        } catch (IOException e) {
            return new ErrorMessage("Error in request handling");
        }
    }
}

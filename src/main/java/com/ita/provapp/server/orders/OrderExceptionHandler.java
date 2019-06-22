package com.ita.provapp.server.orders;

import com.ita.provapp.server.provappcommon.json.ErrorMessage;
import com.ita.provapp.server.mailsender.MailSenderException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class OrderExceptionHandler extends com.ita.provapp.server.provappcommon.ExceptionHandler {

    @ExceptionHandler(MailSenderException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ErrorMessage handlerEntityNotFoundException(HttpServletRequest req, MailSenderException ex) {
        return new ErrorMessage("Order do not exists");
    }
}

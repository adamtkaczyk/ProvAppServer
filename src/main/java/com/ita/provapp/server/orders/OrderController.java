package com.ita.provapp.server.orders;

import com.ita.provapp.server.AppConfiguration;
import com.ita.provapp.server.authentication.AuthenticationController;
import com.ita.provapp.server.json.Order;
import com.ita.provapp.server.mailsender.MailSender;
import com.ita.provapp.server.mailsender.MailSenderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/order")
public class OrderController {

    private AppConfiguration conf = AppConfiguration.getInstance();
    private MailSender mailSender;
    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    public OrderController() {
        int port = Integer.parseInt(conf.getValue("provapp.email.port"));
        mailSender = new MailSender(conf.getValue("provapp.email.host"), port, conf.getValue("provapp.email.from"), conf.getValue("provapp.email.password"));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createOrder(@RequestBody Order order, UriComponentsBuilder uriBuilder) throws MailSenderException {
        logger.info(String.format("Received new order request"));
        int id = 112433434;
        UriComponents uriComponents = uriBuilder.path("/order/{id}").buildAndExpand(id);

        mailSender.send(conf.getValue("provapp.email.to"), "This is Subject", "This is Body");

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}

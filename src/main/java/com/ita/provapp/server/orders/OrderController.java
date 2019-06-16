package com.ita.provapp.server.orders;

import com.ita.provapp.server.AppConfiguration;
import com.ita.provapp.server.common.exceptions.AuthTokenIncorrectException;
import com.ita.provapp.server.common.exceptions.EntityNotFoundException;
import com.ita.provapp.server.common.json.ErrorMessage;
import com.ita.provapp.server.common.json.Order;
import com.ita.provapp.server.mailsender.MailSender;
import com.ita.provapp.server.mailsender.MailSenderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderManager orderManager = new OrderManagerTemporary();
    private AppConfiguration conf = AppConfiguration.getInstance();
    private MailSender mailSender;
    Logger logger = LoggerFactory.getLogger(OrderController.class);

    public OrderController() {
        int port = Integer.parseInt(conf.getValue("provapp.email.port"));
        mailSender = new MailSender(conf.getValue("provapp.email.host"), port, conf.getValue("provapp.email.from"), conf.getValue("provapp.email.password"));
    }

    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity createOrder(@Valid @RequestBody Order order, @RequestHeader("Authorization") String authToken) throws MailSenderException, AuthTokenIncorrectException {
        logger.info(String.format("Received new order request, token: [%s]",authToken));
        Integer orderID = orderManager.addOrder(order,authToken);
        String location = String.format("/order/%d",orderID);

        //mailSender.send(conf.getValue("provapp.email.to"), "This is Subject", "This is Body");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Location",location);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{orderID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Order getOrder(@PathVariable Integer orderID, @RequestHeader("Authorization") String authToken) throws AuthTokenIncorrectException, EntityNotFoundException {
        logger.info(String.format("GET /order/%d. Get order request",orderID));
        return orderManager.getOrder(orderID,authToken);
    }
}

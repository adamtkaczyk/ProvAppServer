package com.ita.provapp.server.orders;

import com.ita.provapp.server.AppConfiguration;
import com.ita.provapp.server.common.exceptions.AuthTokenIncorrectException;
import com.ita.provapp.server.common.exceptions.EntityNotFoundException;
import com.ita.provapp.server.common.json.Order;
import com.ita.provapp.server.mailsender.MailSender;
import com.ita.provapp.server.mailsender.MailSenderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class OrderController {

    @Autowired
    private OrderService orderService;

    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @RequestMapping( value = "/users/{username}/orders", method = RequestMethod.POST)
    public ResponseEntity createOrder(@Valid @RequestBody Order order, @PathVariable String username, @RequestHeader("Authorization") String authToken) throws MailSenderException, AuthTokenIncorrectException {
        logger.info(String.format("POST /users/%s/orders. Received new order request, token: [%s]",username,authToken));
        Integer orderID = orderService.addOrder(order,authToken);
        String location = String.format("/users/%s/order/%d",username,orderID);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Location",location);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/{username}/orders/{orderID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Order getOrder(@PathVariable String username, @PathVariable Integer orderID, @RequestHeader("Authorization") String authToken) throws AuthTokenIncorrectException, EntityNotFoundException {
        logger.info(String.format("GET /users/%s/order/%d. Get order request",username,orderID));
        return orderService.getOrder(orderID,authToken);
    }

    @RequestMapping(value = "/users/{username}/orders", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getOrder(@PathVariable String username, @RequestHeader("Authorization") String authToken) throws AuthTokenIncorrectException, EntityNotFoundException {
        logger.info(String.format("GET /users/%s/order. Get order request",username));
        return null;
    }
}

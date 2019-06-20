package com.ita.provapp.server.orders;

import com.ita.provapp.server.AppConfiguration;
import com.ita.provapp.server.common.exceptions.AuthTokenIncorrectException;
import com.ita.provapp.server.common.exceptions.EntityNotFoundException;
import com.ita.provapp.server.common.json.Order;
import com.ita.provapp.server.mailsender.MailSender;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private MailSender mailSender;
    private AppConfiguration conf = AppConfiguration.getInstance();
    private OrderManagerTemporary orderManagerTemporary = new OrderManagerTemporary();

    public OrderService() {
        int port = Integer.parseInt(conf.getValue("provapp.email.port"));
        mailSender = new MailSender(conf.getValue("provapp.email.host"), port, conf.getValue("provapp.email.from"), conf.getValue("provapp.email.password"));
    }

    public Integer addOrder(Order order, String token) throws AuthTokenIncorrectException {
        mailSender.send(conf.getValue("provapp.email.to"), "This is Subject", "This is Body");

        return orderManagerTemporary.addOrder(order,token);
    }

    public Order getOrder(Integer orderId, String token) throws AuthTokenIncorrectException, EntityNotFoundException {
        return orderManagerTemporary.getOrder(orderId,token);
    }
}

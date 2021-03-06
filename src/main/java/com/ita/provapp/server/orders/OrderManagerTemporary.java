package com.ita.provapp.server.orders;

import com.ita.provapp.server.provappcommon.exceptions.AuthTokenIncorrectException;
import com.ita.provapp.server.provappcommon.exceptions.EntityNotFoundException;
import com.ita.provapp.server.provappcommon.json.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Random;

public class OrderManagerTemporary {
    private ArrayList<Order> orders = new ArrayList<>();
    private static Logger logger = LoggerFactory.getLogger(OrderManagerTemporary.class);
    private Random rand = new Random();

    public Integer addOrder(Order order, String token) throws AuthTokenIncorrectException {
        if(token.isEmpty())
            throw new AuthTokenIncorrectException();
        Integer orderID = rand.nextInt(Integer.MAX_VALUE);
        order.setOrderID(orderID);
        orders.add(order);
        return orderID;
    }

    public Order getOrder(Integer orderId, String token) throws AuthTokenIncorrectException, EntityNotFoundException {
        if(token.isEmpty())
            throw new AuthTokenIncorrectException();
        return findOrder(orderId);
    }

    private boolean orderExists(Integer orderId) {
        return orders.stream().anyMatch(order -> order.getOrderID().equals(orderId));
    }

    private Order getOrder(Integer orderId) {
        return orders.stream().filter(order -> order.getOrderID().equals(orderId)).findFirst().get();
    }

    private Order findOrder(Integer orderID) throws EntityNotFoundException {
        if(orderExists(orderID)) {
            logger.debug("Found order: " + orderID);
            return getOrder(orderID);
        } else {
            logger.warn("Order: " + orderID + " not exists.");
            throw new EntityNotFoundException("Order: " + orderID + " not exists.");
        }
    }
}

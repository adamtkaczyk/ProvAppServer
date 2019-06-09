package com.ita.provapp.server.orders;

import com.ita.provapp.server.authentication.AuthTokenIncorrectException;
import com.ita.provapp.server.exceptions.EntityNotFoundException;
import com.ita.provapp.server.json.Order;

public abstract class OrderManager {
    public abstract Integer addOrder(Order order, String token) throws AuthTokenIncorrectException;
    public abstract Order getOrder(Integer orderId, String token) throws AuthTokenIncorrectException, EntityNotFoundException;
}

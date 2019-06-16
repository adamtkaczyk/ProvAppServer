package com.ita.provapp.server.common.json;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private Integer orderID;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @NotEmpty
    private List<OrderPosition> orderPositions = new ArrayList<>();

    @Valid
    private User user;

    public Order() {
        createTime = new Date();
    }

    public Order(Date createTime) {
        this.createTime = createTime;
    }

    public Order(Integer orderID, Date createTime) {
        this(createTime);
        this.orderID = orderID;
    }

    public Order(Integer orderID, Date createTime, User user, List<OrderPosition> orderPositions) {
        this.orderID = orderID;
        this.createTime = createTime;
        this.user = user;
        this.orderPositions = orderPositions;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public List<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(List<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
    }

    public void addOrderPosition(OrderPosition orderPosition) {
        this.orderPositions.add(orderPosition);
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderPosition> getOrderPosition() {
        return orderPositions;
    }

    public void setOrderPosition(List<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
    }
}

package com.ita.provapp.server.json;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    public class OrderPosition {
        public OrderPosition(Integer number, Product product) {
            this.number = number;
            this.product = product;
        }

        @NotNull
        @Min(0)
        private Integer number;
        @NotNull
        private Product product;

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }
    }

    private Integer orderID;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    List<OrderPosition> orderPositions = new ArrayList<>();

    public Order(Date createTime) {
        this.createTime = createTime;
    }

    public Order(Integer orderID, Date createTime) {
        this(createTime);
        this.orderID = orderID;
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
}

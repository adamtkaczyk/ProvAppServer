package com.ita.provapp.server.json;

import javax.validation.constraints.NotNull;

public class OrderPosition {
    public OrderPosition() {
    }

    public OrderPosition(Integer number) {
        this.number = number;
        //this.product = product;
    }

    public OrderPosition(Integer number, Product product) {
        this.number = number;
        //this.product = product;
    }

    //@NotNull
    //@Min(1)
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

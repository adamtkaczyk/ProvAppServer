package com.ita.provapp.server.json;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Product {
    private Integer productID;
    @NotNull
    private String name;
    @NotNull
    @Min(0)
    private Integer price;
    @NotNull
    private String description;

    public Product() {

    }

    public Product(Integer productId, String name, Integer price, String description) {
        this(name,price,description);
        this.productID = productId;
    }

    public Product(String name, Integer price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

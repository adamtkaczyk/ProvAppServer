package com.ita.provapp.server.common.json;

import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Product {
    private Integer productID;
    @NotEmpty(message = "Product name can't be empty")
    private String name;
    @NotNull
    @Min(value = 0, message = "Product price must be bigger the 0")
    private Integer price;
    @NotEmpty(message = "Product description can't be empty")
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

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof Product)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Product product = (Product) object;

        // Compare the data members and return accordingly
        return name.equals(product.name) && price.equals(product.price) && description.equals(product.description);
    }
}

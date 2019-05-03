package com.ita.provapp.server;

import com.ita.provapp.server.json.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductsController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProductList() {
        List<Product> products = new ArrayList<>();
        Product p1 = new Product();
        p1.setName("product1");
        Product p2 = new Product();
        p2.setName("product2");
        products.add(p1);
        products.add(p2);

        return products;
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct() {
        Product p = new Product();
        p.setName("product");

        return p;
    }
}

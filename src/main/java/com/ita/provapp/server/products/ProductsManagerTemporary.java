package com.ita.provapp.server.products;

import com.ita.provapp.server.common.exceptions.EntityNotFoundException;
import com.ita.provapp.server.common.json.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductsManagerTemporary  {
    private List<Product> products = new ArrayList<>();
    private static Logger logger = LoggerFactory.getLogger(ProductsManagerTemporary.class);
    private Random random = new Random();


    public ProductsManagerTemporary() {
        products.add(new Product(123,"product1",1234,"product1 description"));
        products.add(new Product(124,"product2",2234,"product2 description"));
    }

    public Integer addProduct(Product product) {
        Integer productId = random.nextInt(Integer.MAX_VALUE);
        product.setProductID(productId);
        products.add(product);
        return productId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct(Integer productId) throws EntityNotFoundException {
        return findProduct(productId);
    }

    private boolean productExists(Integer orderId) {
        return products.stream().anyMatch(product -> product.getProductID().equals(orderId));
    }

    private Product findProduct(Integer productID) throws EntityNotFoundException {
        if(productExists(productID)) {
            logger.debug("Found product: " + productID);
            return products.stream().filter(product -> product.getProductID().equals(productID)).findFirst().get();
        } else {
            logger.warn("Product: " + productID + " not exists.");
            throw new EntityNotFoundException("Product: " + productID + " not exists.");
        }
    }
}

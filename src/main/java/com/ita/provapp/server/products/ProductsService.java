package com.ita.provapp.server.products;

import com.ita.provapp.server.provappcommon.exceptions.EntityNotFoundException;
import com.ita.provapp.server.provappcommon.json.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    ProductsManagerTemporary productsManagerTemporary = new ProductsManagerTemporary();

    public Integer addProduct(Product product) {
        return productsManagerTemporary.addProduct(product);
    }

    public List<Product> getProducts() {
        return productsManagerTemporary.getProducts();
    }

    public Product getProduct(Integer productId) throws EntityNotFoundException {
        return productsManagerTemporary.getProduct(productId);
    }
}

package com.ita.provapp.server.products;

import com.ita.provapp.server.common.exceptions.EntityNotFoundException;
import com.ita.provapp.server.common.json.Product;
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

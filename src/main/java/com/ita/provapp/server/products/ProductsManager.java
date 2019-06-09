package com.ita.provapp.server.products;

import com.ita.provapp.server.exceptions.EntityNotFoundException;
import com.ita.provapp.server.json.Product;

import java.util.List;

public abstract class ProductsManager {
    public abstract Integer addProduct(Product product);
    public abstract List<Product> getProducts();
    public abstract Product getProduct(Integer productId) throws EntityNotFoundException;
}

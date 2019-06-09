package com.ita.provapp.server.products;

import com.ita.provapp.server.exceptions.EntityExistsException;
import com.ita.provapp.server.exceptions.EntityNotFoundException;
import com.ita.provapp.server.json.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductsController {
    Logger logger = LoggerFactory.getLogger(ProductsController.class);
    ProductsManager productsManager = new ProductsManagerTemporary();

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addProduct(@RequestBody Product product) {
        logger.info(String.format("POST /product. Add new product request, product name=[%s]",product.getName()));
        int productID = productsManager.addProduct(product);
        String location = String.format("/order/%d",productID);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Location",location);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProductList() {
        logger.info("GET /product. Get products list request");
        return productsManager.getProducts();
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable Integer productId) throws EntityNotFoundException {
        logger.info(String.format("GET /product/%d. Get products list request",productId));
        return productsManager.getProduct(productId);
    }
}

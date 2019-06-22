package com.ita.provapp.server.products;

import com.ita.provapp.server.provappcommon.exceptions.EntityNotFoundException;
import com.ita.provapp.server.provappcommon.json.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    Logger logger = LoggerFactory.getLogger(ProductsController.class);

    @Autowired
    ProductsService productsService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addProduct(@Valid  @RequestBody Product product) {
        logger.info(String.format("POST /products. Add new product request, product name=[%s]",product.getName()));
        int productID = productsService.addProduct(product);
        String location = String.format("/products/%d",productID);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Location",location);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProductList() {
        logger.info("GET /products. Get products list request");
        return productsService.getProducts();
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable Integer productId) throws EntityNotFoundException {
        logger.info(String.format("GET /products/%d. Get product request",productId));
        return productsService.getProduct(productId);
    }
}

package com.ita.provapp.server.products;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ita.provapp.server.common.json.ErrorMessage;
import com.ita.provapp.server.common.json.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductsController.class)
public class ProductsControllerTest {
    private Gson gson = new GsonBuilder().create();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductsService productsService;

    @Test
    public void testAddProduct() throws Exception {
        Product product = new Product("product1", 1298, "product1 description");
        Integer productId = 1234;
        when(productsService.addProduct(product)).thenReturn(1234);

        mockMvc.perform(
                post("/products")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .content(gson.toJson(product))
                )
                .andExpect(header().string("Location", "/products/" + productId))
                .andExpect(status().isCreated()).andReturn();
    }

    @Test
    public void testAddProductEmptyName() throws Exception {
        Product product = new Product("", 1298, "product1 description");
        ErrorMessage errorMessage = new ErrorMessage("Product name can't be empty", 400);

        mockMvc.perform(
                post("/products")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(gson.toJson(product))
                )
                .andExpect(jsonPath("$.errors", org.hamcrest.Matchers.is(errorMessage.getErrors())))
                .andExpect(jsonPath("$.status", org.hamcrest.Matchers.is(errorMessage.getStatus())))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void testAddProductNullName() throws Exception {
        //Product product = new Product("", 1298, "product1 description");
        ErrorMessage errorMessage = new ErrorMessage("Product name can't be empty", 400);
        //String product = "{ \"name\": \"kaktus\", \"price\": 123, \"description\": \"lody do dupy\" }";
        String product = "{ \"price\": 123, \"description\": \"lody do dupy\" }";


        mockMvc.perform(
                post("/products")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(product)
                )
                .andExpect(jsonPath("$.errors", org.hamcrest.Matchers.is(errorMessage.getErrors())))
                .andExpect(jsonPath("$.status", org.hamcrest.Matchers.is(errorMessage.getStatus())))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest()).andReturn();
    }
}

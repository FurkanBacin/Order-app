package com.smartera.product.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartera.product.service.dto.ReadProductDTO;
import com.smartera.product.service.dto.ReadProductForCartDTO;
import com.smartera.product.service.dto.WriteProductDTO;
import com.smartera.product.service.entity.Product;
import com.smartera.product.service.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl productService;


    @Test
    void saveProduct() throws Exception {

        ReadProductDTO readProductDTO = new ReadProductDTO(1, "elma");
        WriteProductDTO writeProductDTO = new WriteProductDTO("elma");

        when(productService.saveProduct(writeProductDTO)).thenReturn(readProductDTO);

        final var resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/products/save-product")
                .content(asJsonString(readProductDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(readProductDTO.getId())));

    }


    @Test
    void getAllProducts() throws Exception {

        List<ReadProductDTO> readProductDTOList = new ArrayList<>();
        ReadProductDTO readProductDTO = new ReadProductDTO(1,"elma");
        ReadProductDTO readProductDTO1 = new ReadProductDTO(2,"armut");
        readProductDTOList.add(readProductDTO);
        readProductDTOList.add(readProductDTO1);

        when(productService.getAllProducts()).thenReturn(readProductDTOList);
        RequestBuilder request = MockMvcRequestBuilders.get("/products/get-all-products")
                .contentType(MediaType.APPLICATION_JSON);

        final var resultActions = mockMvc.perform(request);

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(readProductDTOList.get(0).getId())))
                .andExpect(jsonPath("$[1].id", is(readProductDTOList.get(1).getId())));

    }

    @Test
    void getProductById() throws Exception {

        final var id = 1;
        ReadProductForCartDTO readProductForCartDTO = new ReadProductForCartDTO("elma");

        when(productService.getProductById(id)).thenReturn(readProductForCartDTO);

        final var resultAction = mockMvc.perform(MockMvcRequestBuilders
                .get("/products//get-product-by-id/"+id)
                .accept(MediaType.APPLICATION_JSON));



        resultAction.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("elma"));


        /*
        mockMvc.perform( MockMvcRequestBuilders
                .get("/products/get-prodcut-by-id/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));


         */

    /*
        final var id = 1;
        ReadProductForCartDTO readProductForCartDTO = new ReadProductForCartDTO("elma");

        when(productService.getProductById(id)).thenReturn(readProductForCartDTO);
        RequestBuilder request = MockMvcRequestBuilders.get("/products/get-product-by-id/id");

        mockMvc.perform(request).andReturn();


     */

    }

    @Test
    void deleteProduct() throws Exception {

        final var id =1;
        Boolean status = true;

        when(productService.deleteProductById(id)).thenReturn(status);
        RequestBuilder request = MockMvcRequestBuilders.delete("/products/delete-product/"+id);

        final var resultAction = mockMvc.perform(MockMvcRequestBuilders
                .delete("/products/delete-product/"+id));

        resultAction.andExpect(status().isAccepted());
    }

    @Test
    void updateProductById() throws Exception {

        final var id = 1;
        ReadProductDTO readProductDTO = new ReadProductDTO(1, "elma");
        WriteProductDTO writeProductDTO = new WriteProductDTO("armut");

        when(productService.updateProduct(id, writeProductDTO)).thenReturn(readProductDTO);

        final var expectedDTO = productService.updateProduct(id,writeProductDTO);

        final var resultAction = mockMvc.perform(MockMvcRequestBuilders
                .put("/products/update-product/"+id)
                .content(asJsonString(expectedDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        resultAction.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("elma"));

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
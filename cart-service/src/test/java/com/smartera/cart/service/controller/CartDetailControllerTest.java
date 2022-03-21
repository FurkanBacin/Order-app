package com.smartera.cart.service.controller;

import com.smartera.cart.service.service.CartDetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CartDetailController.class)
class CartDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartDetailServiceImpl cartDetailService;


    @Test
    void getById() {
    }

    @Test
    void getCartDetail() {
    }

    @Test
    void getAllCartDetail() {
    }

    @Test
    void save() {
    }

    @Test
    void deleteCartDetailById() {
    }
}
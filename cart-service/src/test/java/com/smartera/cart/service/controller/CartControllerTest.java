package com.smartera.cart.service.controller;

import com.smartera.cart.service.service.CartServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CartController.class)
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartServiceImpl cartService;


    @Test
    void saveCart() {
    }

    @Test
    void getCart() {
    }

    @Test
    void getCartById() {
    }

    @Test
    void deleteCartById() {
    }
}
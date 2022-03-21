package com.smartera.customer.service.controller;

import com.smartera.customer.service.dto.ReadCustomerDTO;
import com.smartera.customer.service.dto.ReadCustomerForCartDTO;
import com.smartera.customer.service.dto.WriteCustomerDTO;
import com.smartera.customer.service.entity.Customer;
import com.smartera.customer.service.service.CustomerServiceImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(CustomerControllerTest.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerServiceImpl customerService;

    @Test
    void saveCustomer() throws Exception {

        ReadCustomerDTO readCustomerDTO = new ReadCustomerDTO(1,"furkan",true);
        WriteCustomerDTO writeCustomerDTO = new WriteCustomerDTO("furkan",true);

        when(customerService.saveCustomer(writeCustomerDTO)).thenReturn(readCustomerDTO);
        RequestBuilder request = MockMvcRequestBuilders.post("/customers/save-customer");

        mockMvc.perform(request).andReturn();

    }

    @Test
    void getAllCustomer() throws Exception {

        List<ReadCustomerDTO> readCustomerDTOS = new ArrayList<>();

        when(customerService.getAllCustomers()).thenReturn(readCustomerDTOS);
        RequestBuilder request = MockMvcRequestBuilders.get("/customers/get-all-customers");

        mockMvc.perform(request).andReturn();

    }

    @Test
    void getCustomerById() throws Exception {

        final var id = 1;
        ReadCustomerForCartDTO readCustomerForCartDTO = new ReadCustomerForCartDTO("furkan",true);

        when(customerService.getCustomerById(id)).thenReturn(readCustomerForCartDTO);
        RequestBuilder request = MockMvcRequestBuilders.get("/customers/get-customer-by-id/id");

        mockMvc.perform(request).andReturn();
    }

    @Test
    void deleteCustomer() throws Exception {

        Customer customer = new Customer(1,"furkan",true);
        Boolean status= true;

        when(customerService.deleteCustomer(customer.getId())).thenReturn(status);
        RequestBuilder request = MockMvcRequestBuilders.delete("/customers/delete-customer/id");

        mockMvc.perform(request).andReturn();

    }

    @Test
    void updateCustomer() throws Exception {

        ReadCustomerDTO readCustomerDTO = new ReadCustomerDTO(1,"ali",true);
        WriteCustomerDTO writeCustomerDTO = new WriteCustomerDTO("ali",true);

        when(customerService.updateCustomer(1,writeCustomerDTO)).thenReturn(readCustomerDTO);
        RequestBuilder request = MockMvcRequestBuilders.put("/customers/update-customer/id");

        mockMvc.perform(request).andReturn();

    }
}
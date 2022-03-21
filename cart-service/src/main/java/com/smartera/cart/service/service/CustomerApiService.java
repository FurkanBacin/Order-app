package com.smartera.cart.service.service;

import com.smartera.cart.service.client.CustomerServiceClient;
import com.smartera.cart.service.dto.ReadCustomerForCartDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerApiService  {

    private final CustomerServiceClient customerServiceClient;

    public CustomerApiService(CustomerServiceClient customerServiceClient) {
        this.customerServiceClient = customerServiceClient;
    }

    public ReadCustomerForCartDTO getCustomerById(int id ){

        ReadCustomerForCartDTO readCustomerForCartDto = customerServiceClient.getCustomerById(id);
        return readCustomerForCartDto;
    }
}

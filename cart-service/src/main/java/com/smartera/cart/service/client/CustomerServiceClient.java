package com.smartera.cart.service.client;

import com.smartera.cart.service.dto.ReadCustomerForCartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "customer-service" , url = "http://localhost:9002")
public interface CustomerServiceClient {

    @GetMapping("/customers/get-customer-by-id/{id}")
    public ReadCustomerForCartDTO getCustomerById(@PathVariable int id);
}

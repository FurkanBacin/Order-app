package com.smartera.cart.service.client;

import com.smartera.cart.service.dto.ReadProductForCartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "product-service", url = "http://localhost:9001")
public interface ProductServiceClient {

    @GetMapping("/products/get-product-by-id/{id}")
    public ReadProductForCartDTO getProductById(@PathVariable int id);

}

package com.smartera.cart.service.service;

import com.smartera.cart.service.client.ProductServiceClient;
import com.smartera.cart.service.dto.ReadProductForCartDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductApiService {

    private final ProductServiceClient productServiceClient;

    public ProductApiService(ProductServiceClient productServiceClient) {
        this.productServiceClient = productServiceClient;
    }


    public ReadProductForCartDTO getProductById(int id ){

        ReadProductForCartDTO readProductForCartDto =productServiceClient.getProductById(id);
        return readProductForCartDto;
    }
}

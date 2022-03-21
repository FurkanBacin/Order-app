package com.smartera.cart.service.service;

import com.smartera.cart.service.dto.ReadCustomerForCartDTO;
import com.smartera.cart.service.dto.ReadProductForCartDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateService {

    private final RestTemplate restTemplate;

    public RestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ReadProductForCartDTO getProduct(int id){

        ReadProductForCartDTO readProductForCartDto =restTemplate
                .getForObject("http://PRODUCT-SERVICE/products/getProductById/" +id
                        , ReadProductForCartDTO.class);
        return  readProductForCartDto;
    }

    public ReadCustomerForCartDTO getCustomer(int id){

        ReadCustomerForCartDTO readCustomerForCartDto = restTemplate
                .getForObject("http://CUSTOMER-SERVICE/customers/getCustomerById/"+id
                        , ReadCustomerForCartDTO.class);
        return readCustomerForCartDto;
    }
}

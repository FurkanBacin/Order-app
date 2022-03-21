package com.smartera.cart.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplateDTO {

    private ReadCartDTO cart;
    private ReadCustomerForCartDTO customer;
}

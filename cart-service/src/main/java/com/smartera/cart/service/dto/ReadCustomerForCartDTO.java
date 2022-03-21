package com.smartera.cart.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadCustomerForCartDTO {

    private String name;
    private boolean authority;

}
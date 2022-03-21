package com.smartera.customer.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WriteCustomerDTO {

    private String name;
    private boolean authority;
}

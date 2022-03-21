package com.smartera.cart.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WriteCartDetailDTO {

    private WriteCartForCartDetailDTO cartId;
    private int productId;
    private int quantity;
}

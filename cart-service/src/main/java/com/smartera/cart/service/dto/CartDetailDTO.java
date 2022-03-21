package com.smartera.cart.service.dto;

import com.smartera.cart.service.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailDTO {

    private int id;
    private Cart cartId;
    private int productId;
    private int quantity;
}

package com.smartera.cart.service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadCartDetailDTO {

    private CartDetailDTO cartDetail;
    private ReadProductForCartDTO product;
}

package com.smartera.cart.service.service;

import com.smartera.cart.service.dto.CartDTO;
import com.smartera.cart.service.dto.ReadCartDTO;
import com.smartera.cart.service.dto.ResponseTemplateDTO;
import com.smartera.cart.service.dto.WriteCartDTO;


public interface CartService {

    CartDTO saveCart(WriteCartDTO writeCartDto);
    ResponseTemplateDTO getCart(int id);
    ReadCartDTO getCartById (int id);
    Boolean deleteCartById (int id);
}

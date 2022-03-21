package com.smartera.cart.service.service;

import com.smartera.cart.service.dto.CartDetailDTO;
import com.smartera.cart.service.dto.ReadCartDetailDTO;
import com.smartera.cart.service.dto.WriteCartDetailDTO;
import com.smartera.cart.service.entity.CartDetail;

import java.util.List;


public interface CartDetailService {

     CartDetailDTO saveCartDetail(WriteCartDetailDTO writeCartDetailDto);
     ReadCartDetailDTO getCartDetailWithProduct(int id);
     CartDetailDTO getCartDetail(int id);
     boolean deleteCartDetailById(int id);
     List<CartDetail> getAllCartDetail(int id);

}

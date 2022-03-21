package com.smartera.cart.service.controller;

import com.smartera.cart.service.dto.CartDetailDTO;
import com.smartera.cart.service.dto.ReadCartDetailDTO;
import com.smartera.cart.service.dto.WriteCartDetailDTO;
import com.smartera.cart.service.entity.CartDetail;
import com.smartera.cart.service.service.CartDetailServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart-detail")
public class CartDetailController {

    private final CartDetailServiceImpl cartDetailService;


    public CartDetailController(CartDetailServiceImpl cartDetailService) {
        this.cartDetailService = cartDetailService;
    }

    @GetMapping("product-info/{id}")
    public ResponseEntity<ReadCartDetailDTO> getById (@PathVariable int id){

        ReadCartDetailDTO readCartDetailDto = cartDetailService.getCartDetailWithProduct(id);
        return ResponseEntity.ok(readCartDetailDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CartDetailDTO> getCartDetail(@PathVariable int id){

        CartDetailDTO cartDetailDto = cartDetailService.getCartDetail(id);
        return ResponseEntity.ok(cartDetailDto);
    }

    @GetMapping("all-cart-details/{id}")
    public ResponseEntity<List<CartDetail>> getAllCartDetail (@PathVariable int id){

        List<CartDetail> cartDetails = cartDetailService.getAllCartDetail(id);
        return ResponseEntity.ok(cartDetails);
    }

    @PostMapping("/")
    public ResponseEntity<CartDetailDTO> save (@RequestBody WriteCartDetailDTO writeCartDetailDto){

        CartDetailDTO cartDetailDto = cartDetailService.saveCartDetail(writeCartDetailDto);
        return ResponseEntity.ok(cartDetailDto);

    }

    @DeleteMapping("/delete-cart-detail/{id}")
    public ResponseEntity<Boolean> deleteCartDetailById(@PathVariable int id){

        Boolean status = cartDetailService.deleteCartDetailById(id);
        return ResponseEntity.ok(status);
    }

}

package com.smartera.cart.service.controller;

import com.smartera.cart.service.dto.CartDTO;
import com.smartera.cart.service.dto.ReadCartDTO;
import com.smartera.cart.service.dto.ResponseTemplateDTO;
import com.smartera.cart.service.dto.WriteCartDTO;
import com.smartera.cart.service.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/saveCart")
    public ResponseEntity<CartDTO> saveCart(@RequestBody WriteCartDTO writeCartDto) {

        CartDTO resultCart = cartService.saveCart(writeCartDto);
        return ResponseEntity.ok(resultCart);
    }

    @GetMapping("/getCart/{id}")
    public ResponseEntity<ResponseTemplateDTO> getCart(@PathVariable int id) {

        ResponseTemplateDTO responseTemplateDto = cartService.getCart(id);
        return ResponseEntity.ok(responseTemplateDto);
    }

    @GetMapping("/getCartById/{id}")
    public ResponseEntity<ReadCartDTO> getCartById(@PathVariable int id){

        ReadCartDTO readCartDto = cartService.getCartById(id);
        return ResponseEntity.ok(readCartDto);
    }

    @DeleteMapping("/deleteCartById/{id}")
    public ResponseEntity<Boolean> deleteCartById(@PathVariable int id){

        Boolean status = cartService.deleteCartById(id);
        return ResponseEntity.ok(status);
    }

}

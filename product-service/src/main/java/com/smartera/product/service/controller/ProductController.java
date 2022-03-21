package com.smartera.product.service.controller;

import com.smartera.product.service.dto.ReadProductDTO;
import com.smartera.product.service.dto.ReadProductForCartDTO;
import com.smartera.product.service.dto.WriteProductDTO;
import com.smartera.product.service.service.ProductServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping("/save-product")
    @ApiOperation(value=" it is saving new product")
    public ResponseEntity<ReadProductDTO> saveProduct(@RequestBody WriteProductDTO writeProductDto) {

        ReadProductDTO resultProduct = productService.saveProduct(writeProductDto);
        return new ResponseEntity<ReadProductDTO>(resultProduct, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-products")
    @ApiOperation(value=" it will fetch all products")
    public ResponseEntity<List<ReadProductDTO>> getAllProducts(){

        List<ReadProductDTO> products = productService.getAllProducts();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping("/get-product-by-id/{id}")
    @ApiOperation(value=" it will fetch product")
    public ResponseEntity<ReadProductForCartDTO> getProductById(@PathVariable int id){

        ReadProductForCartDTO readProductForCartDto =  productService.getProductById(id);
        return new ResponseEntity<>(readProductForCartDto,HttpStatus.OK);
    }

    @DeleteMapping("/delete-product/{id}")
    @ApiOperation(value=" it will delete product with id")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable int id){

        Boolean status = productService.deleteProductById(id);
        return new ResponseEntity<>(status,HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateProduct/{id}")
    @ApiOperation(value=" it will update product")
    public ResponseEntity<ReadProductDTO> updateProductById(@PathVariable int id , @RequestBody WriteProductDTO writeProductDto) {

        ReadProductDTO resultProduct = productService.updateProduct(id,writeProductDto);
        return new ResponseEntity<>(resultProduct,HttpStatus.OK);
    }
}

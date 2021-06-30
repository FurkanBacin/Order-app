package com.smartera.orderapp.controller;

import com.smartera.orderapp.dto.ProductDto;
import com.smartera.orderapp.model.Product;
import com.smartera.orderapp.service.ProductService;
import com.smartera.orderapp.utilities.result.DataResult;
import com.smartera.orderapp.utilities.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/saveProduct")
    @ApiOperation(value=" it is saving new product")
    public Result saveProduct(@RequestBody Product product) {

        return productService.saveProduct(product);
    }

    @GetMapping("/getAllProducts")
    @ApiOperation(value=" it will fetch all products")
    public DataResult<List<ProductDto>> getAllProducts(){

        return productService.getAllProducts();
    }

    @GetMapping("/getProductById/{id}")
    @ApiOperation(value=" it will fetch product")
    public DataResult<ProductDto> getProductById(@PathVariable int id){

        return productService.getProductById(id);
    }

    @DeleteMapping("/deleteProduct/{id}")
    @ApiOperation(value=" it will delete product with id")
    public Result deleteProductById(@PathVariable int id ){

        return productService.deleteProductById(id);
    }

    @PutMapping("/updateProduct")
    @ApiOperation(value=" it will update product")
    public Result updateProductById(@RequestBody Product product) {

        return productService.updateProductById(product);
    }
}

package com.smartera.orderapp.service;

import com.smartera.orderapp.dto.ProductDto;
import com.smartera.orderapp.dto.converter.ProductConverter;
import com.smartera.orderapp.model.Product;
import com.smartera.orderapp.repository.ProductRepository;
import com.smartera.orderapp.utilities.result.DataResult;
import com.smartera.orderapp.utilities.result.Result;
import com.smartera.orderapp.utilities.result.SuccessDataResult;
import com.smartera.orderapp.utilities.result.SuccessResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public ProductService(ProductRepository productRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }

    public DataResult<List<ProductDto>> getAllProducts() {
        List<Product> product = productRepository.findAll();
        return new SuccessDataResult<>(productConverter.entityToDto(product),"Data Listed");
    }

    public DataResult<ProductDto> getProductById(int id){
        Product product = productRepository.getById(id);
        return new SuccessDataResult<>(productConverter.entityToDto(product),"Data Listed");
    }

    public Result saveProduct(Product product) {

        productRepository.save(product);

        return new SuccessResult("Product added");
    }

    public Result deleteProductById(int id) {

        productRepository.deleteById(id);

        return new SuccessResult("Product deleted");
    }

    public Result updateProductById(Product product) {

        productRepository.save(product);

        return new SuccessResult("Product updated");
    }
}

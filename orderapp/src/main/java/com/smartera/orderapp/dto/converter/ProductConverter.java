package com.smartera.orderapp.dto.converter;

import com.smartera.orderapp.dto.ProductDto;
import com.smartera.orderapp.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductConverter {

    public ProductDto entityToDto(Product product){
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        return dto;
    }

    public List<ProductDto> entityToDto(List<Product>product){
        return product.stream().map(x-> entityToDto(x)).collect(Collectors.toList());
    }

    public Product dtoToEntity(ProductDto dto){
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        return product;
    }
    public List<Product> dtoToEntity(List<ProductDto> dto){
        return dto.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
    }

}

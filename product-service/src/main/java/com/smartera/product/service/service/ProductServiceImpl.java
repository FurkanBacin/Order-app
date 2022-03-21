package com.smartera.product.service.service;

import com.smartera.product.service.dto.ReadProductDTO;
import com.smartera.product.service.dto.ReadProductForCartDTO;
import com.smartera.product.service.dto.WriteProductDTO;
import com.smartera.product.service.entity.Product;
import com.smartera.product.service.exception.ProductApiRequestException;
import com.smartera.product.service.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public ReadProductDTO saveProduct(WriteProductDTO writeProductDto) {

        Product product = modelMapper.map(writeProductDto,Product.class);
        return modelMapper.map(productRepository.save(product), ReadProductDTO.class);
    }

    @Override
    @Transactional
    public List<ReadProductDTO> getAllProducts() {

        List<Product> products = productRepository.findAll();
        List<ReadProductDTO> readProductDTOList = products.stream().map(product -> modelMapper.map(product, ReadProductDTO.class))
                .collect(Collectors.toList());
        return readProductDTOList;
    }

    @Override
    @Transactional
    public ReadProductForCartDTO getProductById(int id) {

        Product product = productRepository.getById(id);
        return modelMapper.map(product, ReadProductForCartDTO.class);
    }

    @Override
    @Transactional
    public boolean deleteProductById(int id) {

        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            productRepository.deleteById(id);
            return true;
        }
        throw new ProductApiRequestException("Geçerli ürün bulunamadı.");
    }

    @Override
    @Transactional
    public ReadProductDTO updateProduct(int id, WriteProductDTO writeProductDto) {

        Optional<Product> resultProduct = productRepository.findById(id);
        if (resultProduct.isPresent()){
            resultProduct.get().setName(writeProductDto.getName());
            return modelMapper.map(productRepository.save(resultProduct.get()), ReadProductDTO.class);
        }
        throw new ProductApiRequestException("Geçerli ürün bulunamadı.");
    }

}

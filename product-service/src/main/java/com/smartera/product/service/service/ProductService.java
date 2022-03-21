package com.smartera.product.service.service;


import com.smartera.product.service.dto.ReadProductDTO;
import com.smartera.product.service.dto.ReadProductForCartDTO;
import com.smartera.product.service.dto.WriteProductDTO;

import java.util.List;

public interface ProductService {

    ReadProductDTO saveProduct (WriteProductDTO writeProductDto);
    List<ReadProductDTO> getAllProducts ();
    ReadProductForCartDTO getProductById(int id);
    boolean deleteProductById(int id);
    ReadProductDTO updateProduct(int id, WriteProductDTO writeProductDto);

}

package com.onlinestore.onlinestore.Services;


import com.onlinestore.onlinestore.DTO.FakeStoreProductDTO;
import com.onlinestore.onlinestore.DTO.GenericProductDTO;

import java.util.List;

public interface ProductService {
    GenericProductDTO getProductById(Long id);

    List<GenericProductDTO> getAllProduct();

    void deleteProductById( Long id );

    void updateProductById(Long id);

    void createProduct();
}

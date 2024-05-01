package com.onlinestore.onlinestore.Services;


import com.onlinestore.onlinestore.DTO.GenericProductDTO;
import com.onlinestore.onlinestore.Exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    GenericProductDTO getProductById(String authToken, Long id) throws ProductNotFoundException;

    List<GenericProductDTO> getAllProduct();

    GenericProductDTO deleteProductById( Long id );

    GenericProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO);

    GenericProductDTO createProduct(GenericProductDTO genericProductDTO);
}

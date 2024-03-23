package com.onlinestore.onlinestore.Services;


import com.onlinestore.onlinestore.DTO.FakeStoreProductDTO;

public interface ProductService {
    FakeStoreProductDTO getProductById(Long id);

    String getAllProduct();

    void deleteProductById( Long id );

    void updateProductById(Long id);

    void createProduct();
}

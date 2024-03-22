package com.onlinestore.onlinestore.Services;



public interface ProductService {
    String getProductById(Long id);

    String getAllProduct();

    void deleteProductById( Long id );

    void updateProductById(Long id);

    void createProduct();
}

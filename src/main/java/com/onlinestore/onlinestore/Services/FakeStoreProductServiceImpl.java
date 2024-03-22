package com.onlinestore.onlinestore.Services;

import org.springframework.stereotype.Service;

//@Service // this is create object while initialization. these are called as beans. we can name these beans eg: @Service("FakeStoreBeans")
@Service("FakeStoreProductServiceImpl")
public class  FakeStoreProductServiceImpl implements ProductService{
    @Override
    public String getProductById(Long id) {
        // Integrate Fakestore API here
        return "in fakestore service id is :" + id;
    }

    @Override
    public String getAllProduct() {
        return null;
    }

    @Override
    public void deleteProductById(Long id) {

    }

    @Override
    public void updateProductById(Long id) {

    }

    @Override
    public void createProduct() {

    }
}

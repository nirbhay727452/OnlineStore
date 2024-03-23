package com.onlinestore.onlinestore.Services;

import com.onlinestore.onlinestore.DTO.FakeStoreProductDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//@Service // this is create object while initialization. these are called as beans. we can name these beans eg: @Service("FakeStoreBeans")
@Service("FakeStoreProductServiceImpl")
public class  FakeStoreProductServiceImpl implements ProductService{

    private  RestTemplateBuilder restTemplateBuilder;
    FakeStoreProductServiceImpl( RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    private String getProductURL = "https://fakestoreapi.com/products/";
    @Override
    public FakeStoreProductDTO getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.getForEntity(getProductURL+id, FakeStoreProductDTO.class);
        // Integrate Fakestore API here
        return responseEntity.getBody();
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

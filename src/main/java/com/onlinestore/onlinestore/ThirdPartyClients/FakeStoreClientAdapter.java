package com.onlinestore.onlinestore.ThirdPartyClients;

import com.onlinestore.onlinestore.DTO.FakeStoreProductDTO;
import com.onlinestore.onlinestore.DTO.GenericProductDTO;
import com.onlinestore.onlinestore.Exceptions.ProductNotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class FakeStoreClientAdapter {
    private RestTemplateBuilder restTemplateBuilder;


    FakeStoreClientAdapter( RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    private String singleProductURL = "https://fakestoreapi.com/products/{id}";
    private String genericProductURL = "https://fakestoreapi.com/products/";

    public FakeStoreProductDTO getProductById(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        // Integrate Fakestore API here
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.getForEntity(singleProductURL, FakeStoreProductDTO.class, id);
        // Convert FakeStoreDto into GenericProductDTO before returning
        if(responseEntity.getBody() == null) {
            throw new ProductNotFoundException("Product with id " + id + " doesn't exists");
        }
        return responseEntity.getBody();
    }

    public List<FakeStoreProductDTO> getAllProduct() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> responseEntity = restTemplate.getForEntity(genericProductURL,FakeStoreProductDTO[].class);

        List<FakeStoreProductDTO> result= new ArrayList<>();
        List<FakeStoreProductDTO> fakeStoreProductDTOList = List.of(responseEntity.getBody());
        for(FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOList){
            result.add(fakeStoreProductDTO);
        }
        return result;
    }

    public FakeStoreProductDTO createProduct(GenericProductDTO genericProductDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> responseEntity =
                restTemplate.postForEntity(genericProductURL, genericProductDTO , FakeStoreProductDTO.class);

        return responseEntity.getBody();
    }
    public FakeStoreProductDTO deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        // this call will not return anything so implementing manual call
        //restTemplate.delete(singleProductURL,id);

        //copied below code from getForEntity() and modified objects
        RequestCallback requestCallback =restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.execute(singleProductURL, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return responseEntity.getBody();
    }

    public FakeStoreProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(singleProductURL,id);
        ResponseEntity<FakeStoreProductDTO> responseEntity =
                restTemplate.postForEntity(singleProductURL,genericProductDTO, FakeStoreProductDTO.class);
        return responseEntity.getBody();
    }

}

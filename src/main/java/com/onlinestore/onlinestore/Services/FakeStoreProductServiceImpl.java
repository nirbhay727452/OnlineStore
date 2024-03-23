package com.onlinestore.onlinestore.Services;

import com.onlinestore.onlinestore.DTO.FakeStoreProductDTO;
import com.onlinestore.onlinestore.DTO.GenericProductDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//@Service // this is create object while initialization. these are called as beans. we can name these beans eg: @Service("FakeStoreBeans")
@Service("FakeStoreProductServiceImpl")
public class  FakeStoreProductServiceImpl implements ProductService{

    private final RestTemplateBuilder restTemplateBuilder;


    FakeStoreProductServiceImpl( RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    private String singleProductURL = "https://fakestoreapi.com/products/{id}";
    private String genericProductURL = "https://fakestoreapi.com/products/";

    private static GenericProductDTO convertToGenericProductDTO( FakeStoreProductDTO fakeStoreProductDTO){
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId(fakeStoreProductDTO.getId());
        genericProductDTO.setDescription(fakeStoreProductDTO.getDescription());
        genericProductDTO.setCategory(fakeStoreProductDTO.getCategory());
        genericProductDTO.setImage(fakeStoreProductDTO.getImage());
        genericProductDTO.setTitle(fakeStoreProductDTO.getTitle());
        genericProductDTO.setPrice(fakeStoreProductDTO.getPrice());
        return genericProductDTO;
    }
    @Override
    public GenericProductDTO getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        // Integrate Fakestore API here
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.getForEntity(singleProductURL, FakeStoreProductDTO.class, id);
        // Convert FakeStoreDto into GenericProductDTO before returning
        return convertToGenericProductDTO(responseEntity.getBody());
    }

    @Override
    public List<GenericProductDTO> getAllProduct() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> responseEntity = restTemplate.getForEntity(genericProductURL,FakeStoreProductDTO[].class);

        List<GenericProductDTO> result= new ArrayList<>();
        List<FakeStoreProductDTO> fakeStoreProductDTOList = List.of(responseEntity.getBody());
        for(FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOList){
            result.add(convertToGenericProductDTO(fakeStoreProductDTO));
        }
        return result;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> responseEntity = 
                restTemplate.postForEntity(genericProductURL, genericProductDTO , FakeStoreProductDTO.class);

        return convertToGenericProductDTO(responseEntity.getBody());
    }
    @Override
    public GenericProductDTO deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        // this call will not return anything so implementing manual call
        //restTemplate.delete(singleProductURL,id);

        //copied below code from getForEntity() and modified objects
        RequestCallback requestCallback =restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.execute(singleProductURL, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return convertToGenericProductDTO(responseEntity.getBody());
    }

    @Override
    public void updateProductById(Long id) {

    }


}

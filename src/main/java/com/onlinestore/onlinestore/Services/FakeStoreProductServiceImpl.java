package com.onlinestore.onlinestore.Services;

import com.onlinestore.onlinestore.DTO.FakeStoreProductDTO;
import com.onlinestore.onlinestore.DTO.GenericProductDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//@Service // this is create object while initialization. these are called as beans. we can name these beans eg: @Service("FakeStoreBeans")
@Service("FakeStoreProductServiceImpl")
public class  FakeStoreProductServiceImpl implements ProductService{

    private final RestTemplateBuilder restTemplateBuilder;

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
    FakeStoreProductServiceImpl( RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    private String getProductURL = "https://fakestoreapi.com/products/{id}";
    private String getAllProductURL = "https://fakestoreapi.com/products/";
    @Override
    public GenericProductDTO getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        // Integrate Fakestore API here
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.getForEntity(getProductURL, FakeStoreProductDTO.class, id);
        // Convert FakeStoreDto into GenericProductDTO before returning
        return convertToGenericProductDTO(responseEntity.getBody());
    }

    @Override
    public List<GenericProductDTO> getAllProduct() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> responseEntity = restTemplate.getForEntity(getAllProductURL,FakeStoreProductDTO[].class);

        List<GenericProductDTO> result= new ArrayList<>();
        List<FakeStoreProductDTO> fakeStoreProductDTOList = List.of(responseEntity.getBody());
        for(FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOList){
            result.add(convertToGenericProductDTO(fakeStoreProductDTO));
        }
        return result;
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

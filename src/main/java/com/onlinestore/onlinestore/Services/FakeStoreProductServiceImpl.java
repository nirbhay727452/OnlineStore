package com.onlinestore.onlinestore.Services;

import com.onlinestore.onlinestore.DTO.FakeStoreProductDTO;
import com.onlinestore.onlinestore.DTO.GenericProductDTO;
import com.onlinestore.onlinestore.Exceptions.ProductNotFoundException;
import com.onlinestore.onlinestore.ThirdPartyClients.FakeStoreClientAdapter;
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

    private FakeStoreClientAdapter fakeStoreClientAdapter;


    FakeStoreProductServiceImpl( FakeStoreClientAdapter fakeStoreClientAdapter){
        this.fakeStoreClientAdapter = fakeStoreClientAdapter;
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
    public GenericProductDTO getProductById(Long id) throws ProductNotFoundException {
        GenericProductDTO genericProductDTO = convertToGenericProductDTO(fakeStoreClientAdapter.getProductById(id));
        return genericProductDTO;
    }

    @Override
    public List<GenericProductDTO> getAllProduct() {
        List<GenericProductDTO> result= new ArrayList<>();
        List<FakeStoreProductDTO> fakeStoreProductDTOList = fakeStoreClientAdapter.getAllProduct();
        for(FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOList){
            result.add(convertToGenericProductDTO(fakeStoreProductDTO));
        }
        return result;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
        return convertToGenericProductDTO(fakeStoreClientAdapter.createProduct(genericProductDTO));
    }
    @Override
    public GenericProductDTO deleteProductById(Long id) {
        return convertToGenericProductDTO(fakeStoreClientAdapter.deleteProductById(id));
    }

    @Override
    public GenericProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO) {
        return convertToGenericProductDTO(fakeStoreClientAdapter.updateProductById(id, genericProductDTO));
    }


}

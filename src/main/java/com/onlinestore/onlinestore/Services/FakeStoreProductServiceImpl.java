package com.onlinestore.onlinestore.Services;

import com.onlinestore.onlinestore.DTO.FakeStoreProductDTO;
import com.onlinestore.onlinestore.DTO.GenericProductDTO;
import com.onlinestore.onlinestore.Exceptions.ProductNotFoundException;
import com.onlinestore.onlinestore.Security.JwtObject;
import com.onlinestore.onlinestore.Security.TokenValidator;
import com.onlinestore.onlinestore.ThirdPartyClients.FakeStoreClientAdapter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Service // this is create object while initialization. these are called as beans. we can name these beans eg: @Service("FakeStoreBeans")
@Service("FakeStoreProductServiceImpl")
public class  FakeStoreProductServiceImpl implements ProductService{

    private FakeStoreClientAdapter fakeStoreClientAdapter;
    private  TokenValidator tokenValidator;

    FakeStoreProductServiceImpl(FakeStoreClientAdapter fakeStoreClientAdapter, TokenValidator tokenValidator){
        this.fakeStoreClientAdapter = fakeStoreClientAdapter;
        this.tokenValidator = tokenValidator;
    }


    private final String singleProductURL = "https://fakestoreapi.com/products/{id}";
    private final String genericProductURL = "https://fakestoreapi.com/products/";

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
    public GenericProductDTO getProductById(String authToken, Long id) throws ProductNotFoundException {
        GenericProductDTO genericProductDTO = convertToGenericProductDTO(fakeStoreClientAdapter.getProductById(id));
        System.out.println(authToken);
        Optional<JwtObject> jwtOptional = tokenValidator.validateToken(authToken);
        if(jwtOptional.isEmpty()){
            //invalid request, reject request
            return null;
        }

        JwtObject jwtObject = jwtOptional.get();
        long userId = jwtObject.getUserID();
        // we can validate any condition
        //i.e if this particular user is allowed to make this particular API call or not
        if(userId == 10) // user10 is not allowed to make this call
            return null;

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

package com.onlinestore.onlinestore.Controller;

import com.onlinestore.onlinestore.DTO.ExceptionDTO;
import com.onlinestore.onlinestore.DTO.FakeStoreProductDTO;
import com.onlinestore.onlinestore.DTO.GenericProductDTO;
import com.onlinestore.onlinestore.Exceptions.ProductNotFoundException;
import com.onlinestore.onlinestore.Services.FakeStoreProductServiceImpl;
import com.onlinestore.onlinestore.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Products") // this products endpoint will apply to all methods inside this class
// if not used this annotation then have to map this in every funtion
//eg: @GetMapping("/Products/{id}")
//No Buisness Logic here. all the heavy-lifting will be done by services class. Just call those functions
public class ProductController {
    // We need ProductService object here to call those functions. Code to Interface.
    // No two concrete classes should be dependent on each other. so use Interface.


    //Field Injection
/*

    @Autowired // This is Field Injection and its not recommended read https://stackoverflow.com/questions/39890849/what-exactly-is-field-injection-and-how-to-avoid-it
    @Qualifier("FakeStoreProductServiceImpl")
    private ProductService productService;

*/


    //Setter Injection
/*
    private ProductService productService;
    @Autowired
    public void setProductService(ProductService productService){
        this.productService = productService;
    }

*/

    //Constructor Injection

/* Constructor Injection V1
    // if ProductService Interface has multiple beans then also this will throw error. to solve that we have to name beans and use ProductController(@Qualifier("Bean Name"))
    @Autowired // not required in newer versions
    public ProductController(ProductService productService) { // we will get error here bcz, boot will automaticallty create obj of ProductController, but to create ProductControler obj ProductService obj should be available BeforeHand. SO habe to tell spring boot(define @Service ) ProductService is special class and cretes its object beforehand
        this.productService = productService;
    }
*/

    //Constructor Injection v2 , can be done automatically by using @Autowired on productService declaration == Field Injection
    private ProductService productService;
    //@Autowired // optional in latest version
    ProductController(@Qualifier("FakeStoreProductServiceImpl") ProductService productService) // using qualify to select particular beans if multiple beans available (multple implentation of ProductService Intetrface)
    {
        this.productService = productService;
    }


    @GetMapping("/{id}") //get product by id
    public GenericProductDTO getProductById(@RequestHeader(HttpHeaders.AUTHORIZATION)String authToken , @PathVariable("id") Long id) throws ProductNotFoundException { // map url path id to this local variable id
        // Calls ProductService getProductById
        return productService.getProductById(authToken , id);
    }

    @PostMapping("/")
    public GenericProductDTO createProduct( @RequestBody GenericProductDTO genericProductDTO)
    {
        return productService.createProduct( genericProductDTO);
    }
    @GetMapping("/")
    public List<GenericProductDTO> getAllProduct(){
            return productService.getAllProduct();
    }


    @DeleteMapping("/{id}")
    public GenericProductDTO deleteProductById(@PathVariable("id") Long id ){ // map url path id to this local variable id
        return productService.deleteProductById(id);
    }

    @PatchMapping("/{id}")
    public GenericProductDTO updateProductById(@PathVariable("id") long id, @RequestBody GenericProductDTO genericProductDTO){
        return productService.updateProductById(id, genericProductDTO);
    }

}
/*
3 ways of Dependency Injection
1. Constructor Injection (preffered)
2. Field Injection
3. Setter Injection

 */
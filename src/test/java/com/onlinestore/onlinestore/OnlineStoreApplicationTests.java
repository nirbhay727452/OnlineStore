package com.onlinestore.onlinestore;

import com.onlinestore.onlinestore.Models.Category;
import com.onlinestore.onlinestore.Models.Products;
import com.onlinestore.onlinestore.Repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OnlineStoreApplicationTests {
    @Autowired
    private ProductRepository productRepository;
    @Test
    void SaveDataToDB() {
        Category category1 = new Category();
        category1.setName("Apple_Devices");

        Products product1 = new Products();
        //product1.setCategory(category1);
        product1.setImage("https://tandomurl.png");
        product1.setTitle("Iphone pro15");

        Products savedproduct = productRepository.save(product1);

    }


}

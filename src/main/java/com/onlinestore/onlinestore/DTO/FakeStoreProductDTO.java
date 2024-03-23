package com.onlinestore.onlinestore.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {
    private long id;
    private String title;
    private Float price;
    private String category;
    private String description;
    private String image;
}

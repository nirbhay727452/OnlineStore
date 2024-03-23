package com.onlinestore.onlinestore.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDTO {
    private long id;
    private String description;
    private String title;
    private Float price;
    private String category;
    private String image;
}
/*
Implement thus DTO bcz, if FakeStore Website changes/add attributes into API,
we only have to chnage FakeStoreProductDTO but not our internal code.
 */
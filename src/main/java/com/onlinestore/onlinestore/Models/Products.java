package com.onlinestore.onlinestore.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Products extends BaseModel{
    private String title;
    private String description;
    private String price;
    private String image;
    @ManyToOne
    @JoinColumn(name = "Category_id")
    private Category category;
}

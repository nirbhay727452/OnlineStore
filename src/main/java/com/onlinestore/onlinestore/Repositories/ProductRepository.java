package com.onlinestore.onlinestore.Repositories;

import com.onlinestore.onlinestore.Models.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Products, UUID> {

}

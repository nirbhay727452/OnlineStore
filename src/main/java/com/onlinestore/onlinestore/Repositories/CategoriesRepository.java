package com.onlinestore.onlinestore.Repositories;

import com.onlinestore.onlinestore.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriesRepository extends JpaRepository<Category, UUID> {

}

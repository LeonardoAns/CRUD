package com.poduct.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poduct.category.entity.category.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	Category findCategoryByName(String name);
}

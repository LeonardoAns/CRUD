package com.poduct.category.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.poduct.category.entity.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	Product findProductByName(String name);
}

package com.poduct.category.entity.product;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.poduct.category.entity.category.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String description;
	private Double price;
	private Integer quantity;
	
	@ManyToOne
	@JsonManagedReference
	private Category category;
}


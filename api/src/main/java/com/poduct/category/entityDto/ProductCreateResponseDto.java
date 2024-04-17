package com.poduct.category.entityDto;

import com.poduct.category.entity.category.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateResponseDto {

	private Long id;
	private String name;
	private String description;
	private Double price;
	private Integer quantity;
	private Category category;
}

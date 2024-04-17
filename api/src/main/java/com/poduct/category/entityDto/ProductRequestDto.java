package com.poduct.category.entityDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto{
		
		private String name;
		private String description;
		private Double price;
		private Integer quantity;
		private Long category;
}

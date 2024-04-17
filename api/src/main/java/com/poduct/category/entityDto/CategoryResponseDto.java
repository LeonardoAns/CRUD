package com.poduct.category.entityDto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDto {

	private Long id;
	private String name;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<ProductResponseDto> products;
}

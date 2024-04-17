package com.poduct.category.controller;

import java.net.URI;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.poduct.category.entityDto.CategoryCreateDto;
import com.poduct.category.entityDto.CategoryRequestDto;
import com.poduct.category.entityDto.CategoryResponseDto;
import com.poduct.category.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
	
	private final CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<CategoryCreateDto> createProduct(@RequestBody CategoryRequestDto requestDto){
		
		CategoryCreateDto response = this.categoryService.insertCategory(requestDto);
		
		 String location = ServletUriComponentsBuilder.fromCurrentRequest()
	                .path("api/products/{id}")
	                .buildAndExpand(response.getId())
	                .toUriString();
		 
	     return ResponseEntity.created(URI.create(location)).body(new CategoryCreateDto(response.getId()));

	}
	
	@GetMapping
	public ResponseEntity<List<CategoryResponseDto>> findAll(){
		var products = this.categoryService.findAllCategory();
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponseDto> findCategoryById(@PathVariable Long id){
		return ResponseEntity.ok(this.categoryService.findCategoryById(id));	
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategoryById(Long id){
		return ResponseEntity.noContent().build();
	}
}

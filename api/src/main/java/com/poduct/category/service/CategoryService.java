package com.poduct.category.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.poduct.category.entity.category.Category;
import com.poduct.category.entity.category.exception.CategoryAlreadyExistsException;
import com.poduct.category.entity.category.exception.CategoryNotFoundException;
import com.poduct.category.entityDto.CategoryCreateDto;
import com.poduct.category.entityDto.CategoryRequestDto;
import com.poduct.category.entityDto.CategoryResponseDto;
import com.poduct.category.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	private final ModelMapper modelMapper;
	
	public CategoryCreateDto insertCategory(CategoryRequestDto categoryRequestDto) {
		String transformedName = categoryRequestDto.getName().toLowerCase().replace(" ","");
		
		Category existingCategory = this.categoryRepository.findCategoryByName(transformedName);
		if(existingCategory != null) {
			throw new CategoryAlreadyExistsException("Categoria já existe");
		}
		
		Category category = modelMapper.map(categoryRequestDto, Category.class);
		category.setName(transformedName);
		
		category = this.categoryRepository.save(category);
		
		CategoryCreateDto response = modelMapper.map(category, CategoryCreateDto.class);
		return response;
	}

	
	public List<CategoryResponseDto> findAllCategory(){
	    List<Category> categories = this.categoryRepository.findAll();
	    return categories.stream()
	            .map(category -> modelMapper.map(category, CategoryResponseDto.class))
	            .collect(Collectors.toList());
	}

	
	public CategoryResponseDto findCategoryById(Long id) {
		Category category = findId(id);
		
		return modelMapper.map(category, CategoryResponseDto.class);
	}
	
	
	public void deleteById(Long id) {
		findId(id);
		this.categoryRepository.deleteById(id);
	}
	

	private Category findId(Long id) {
		return this.categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Id não existe"));
	}
}

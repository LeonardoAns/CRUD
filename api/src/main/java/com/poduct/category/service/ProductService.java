package com.poduct.category.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.poduct.category.entity.category.Category;
import com.poduct.category.entity.product.Product;
import com.poduct.category.entity.product.exception.NotFoundProductException;
import com.poduct.category.entity.product.exception.ProductAlreadyExistsException;
import com.poduct.category.entityDto.ProductCreateResponseDto;
import com.poduct.category.entityDto.ProductRequestDto;
import com.poduct.category.entityDto.ProductResponseDto;
import com.poduct.category.repository.CategoryRepository;
import com.poduct.category.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	
	private final ProductRepository productRepository;
	private final ModelMapper modelMapper;
	private final CategoryRepository categoryRepository;  
		
	 public ProductCreateResponseDto insertProduct(ProductRequestDto productRequestDto) {
	        String transformedName = productRequestDto.getName().toLowerCase().replace(" ", "");

	        Product existingProduct = this.productRepository.findProductByName(transformedName);
	        if (existingProduct != null) {
	            throw new ProductAlreadyExistsException("Já existe um produto cadastrado com esse nome");
	        }

	        Category category = this.categoryRepository.findById(productRequestDto.getCategory())
	                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

	        Product product = modelMapper.map(productRequestDto, Product.class);
	        product.setName(transformedName);
	        product.setCategory(category);

	        product = this.productRepository.save(product);

	        ProductCreateResponseDto response = modelMapper.map(product, ProductCreateResponseDto.class);
	        return response;
	    }

	
	public List<ProductResponseDto> findAllProducts(){
		List<Product> products = this.productRepository.findAll();
		return products.stream()
                .map(product -> modelMapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());
	
	}
	
	public ProductResponseDto findById(Long id) {
		Product product = findId(id);
		return modelMapper.map(product, ProductResponseDto.class);
	}
	
	public void deleteProduct(Long id) {
		 findId(id);
		 this.productRepository.deleteById(id);
	}
	
	private Product findId(Long id) {
		return this.productRepository.findById(id).orElseThrow(() -> new NotFoundProductException("Id não existe"));
	}
}

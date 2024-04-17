package com.poduct.category.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.poduct.category.entity.product.Product;
import com.poduct.category.entityDto.ProductCreateResponseDto;
import com.poduct.category.entityDto.ProductRequestDto;
import com.poduct.category.entityDto.ProductResponseDto;
import com.poduct.category.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	@PostMapping
	public ResponseEntity<ProductCreateResponseDto> createProduct(@RequestBody ProductRequestDto productDto){
		ProductCreateResponseDto response = this.productService.insertProduct(productDto);
	    return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<ProductResponseDto>> findAllProducts(){
		var products = this.productService.findAllProducts();
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDto> findProductById(@PathVariable Long id){
		return ResponseEntity.ok(this.productService.findById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProuctsById(@PathVariable Long id){
		this.productService.deleteProduct(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}

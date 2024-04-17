package com.poduct.category.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.poduct.category.entity.category.exception.CategoryAlreadyExistsException;
import com.poduct.category.entity.category.exception.CategoryNotFoundException;
import com.poduct.category.entity.product.exception.NotFoundProductException;
import com.poduct.category.entity.product.exception.ProductAlreadyExistsException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionEntityHandler {
	
	@ExceptionHandler(NotFoundProductException.class)
	public ResponseEntity<ExceptionMessage> notFoundProduct(NotFoundProductException e, HttpServletRequest request){
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.contentType(MediaType.APPLICATION_JSON)
				.body(new ExceptionMessage(request,HttpStatus.NOT_FOUND,e.getMessage()));
	}
	
	@ExceptionHandler(ProductAlreadyExistsException.class)
	public ResponseEntity<ExceptionMessage> productAlreadyExists(ProductAlreadyExistsException p, HttpServletRequest request){
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.contentType(MediaType.APPLICATION_JSON)
				.body(new ExceptionMessage(request,HttpStatus.CONFLICT,p.getMessage()));
	}
	
	@ExceptionHandler(CategoryAlreadyExistsException.class)
	public ResponseEntity<ExceptionMessage> categoryAlreadyExists(CategoryAlreadyExistsException c, HttpServletRequest request){
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.contentType(MediaType.APPLICATION_JSON)
				.body(new ExceptionMessage(request,HttpStatus.CONFLICT,c.getMessage()));
	}
	
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<ExceptionMessage> notFoundProduct(CategoryNotFoundException n, HttpServletRequest request){
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.contentType(MediaType.APPLICATION_JSON)
				.body(new ExceptionMessage(request,HttpStatus.NOT_FOUND,n.getMessage()));
	}
}

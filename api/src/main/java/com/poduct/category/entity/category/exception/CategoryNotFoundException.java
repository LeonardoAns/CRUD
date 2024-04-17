package com.poduct.category.entity.category.exception;

public class CategoryNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public CategoryNotFoundException(String msg) {
		super(msg);
	}
}

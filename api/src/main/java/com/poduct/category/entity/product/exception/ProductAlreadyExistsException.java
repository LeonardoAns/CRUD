package com.poduct.category.entity.product.exception;

public class ProductAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ProductAlreadyExistsException(String msg) {
		super(msg);
	}

}

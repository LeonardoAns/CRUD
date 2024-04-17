package com.poduct.category.entity.product.exception;

public class NotFoundProductException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public NotFoundProductException(String msg) {
		super(msg);
	}

}

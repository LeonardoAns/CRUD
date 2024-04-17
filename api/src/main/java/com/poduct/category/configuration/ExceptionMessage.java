package com.poduct.category.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ExceptionMessage {

	private String path;
	private String method;
	private Integer statusCode;
	private String statusText;
	private String message;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Map<String, String> errors;
	
	public ExceptionMessage() {
		
	}
	
	public ExceptionMessage(HttpServletRequest request, HttpStatus status, String message) {
		this.path = request.getRequestURI();
		this.method = request.getMethod();
		this.statusCode = status.value();
		this.statusText = status.getReasonPhrase();
		this.message = message;	
	}
	
	public ExceptionMessage(HttpServletRequest request, HttpStatus status, String message, BindingResult result) {
		this.path = request.getRequestURI();
		this.method = request.getMethod();
		this.statusCode = status.value();
		this.statusText = status.getReasonPhrase();
		this.message = message;
		addErrors(result);
	}
	
	public void addErrors(BindingResult result) {
		this.errors = new HashMap<>();
		for(FieldError fieldError : result.getFieldErrors()) {
			this.errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
	}
}
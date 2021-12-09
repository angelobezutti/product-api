package com.bzt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> productNotFound(ResourceNotFoundException exception, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(HttpStatus.NOT_FOUND.value());
		errorDetails.setMessage(exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> allException(ResourceNotFoundException exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorDetails.setMessage(exception.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
    }
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDetails> pageUnavailable(ResourceNotFoundException exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
		errorDetails.setMessage(exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

}

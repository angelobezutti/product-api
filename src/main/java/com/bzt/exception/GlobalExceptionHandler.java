package com.bzt.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	 private String typeError = "";

	 @ResponseStatus(code = HttpStatus.BAD_REQUEST)
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	 public ErrorDetails handle(MethodArgumentNotValidException exception) {
		 exception.getBindingResult().getFieldErrors().forEach((error) -> { this.typeError += "\\" + error.getField() + ": " + error.getDefaultMessage() + ". ";});
		 ErrorDetails exceptionResponse = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), typeError);
		 this.typeError = "";
		 return exceptionResponse;
	 }
	    
	 @ResponseStatus(code = HttpStatus.BAD_REQUEST)
	 @ExceptionHandler(MethodArgumentTypeMismatchException.class)
	    public ErrorDetails pageUnavailable(MethodArgumentTypeMismatchException exception) {
	        return new ErrorDetails( HttpStatus.BAD_REQUEST.value(), "Page Unavailable");
	 }
	    
	 @ResponseStatus(code = HttpStatus.NOT_FOUND)
	 @ExceptionHandler(EntityNotFoundException.class)
	    public ErrorDetails notFoundEntity(EntityNotFoundException exception) {
	        return new ErrorDetails( HttpStatus.NOT_FOUND.value(), "Product Not Found");
	 }

	 @ResponseStatus(code = HttpStatus.NOT_FOUND)
	 @ExceptionHandler(EmptyResultDataAccessException.class)
	    public ErrorDetails notFoundDelete(EmptyResultDataAccessException exception) {
	        return new ErrorDetails( HttpStatus.NOT_FOUND.value(), "Product Not Found");
	 }
	    
	 @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR )
	 @ExceptionHandler(Exception.class)
	    public ErrorDetails allException(Exception exception) {
	        return new ErrorDetails( HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Error");
	 }
	    
	 @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	 @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	    public ErrorDetails notAllowed(HttpRequestMethodNotSupportedException exception) {
	        return new ErrorDetails( HttpStatus.METHOD_NOT_ALLOWED.value(), "Method Not Allowed");
	 }
}

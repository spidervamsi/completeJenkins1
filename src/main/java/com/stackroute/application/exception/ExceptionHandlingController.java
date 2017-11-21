package com.stackroute.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomExceptionResponse> invalidInput(MethodArgumentNotValidException exception) {
		
		BindingResult result = exception.getBindingResult();
        CustomExceptionResponse exceptionresponse = new CustomExceptionResponse();
        
        exceptionresponse.setErrorMessage("Invalid Inputs.");
        exceptionresponse.setErrors(result.getFieldErrors().get(0).getDefaultMessage());
        
        return new ResponseEntity<CustomExceptionResponse>(exceptionresponse, HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(InternalRepositoryException.class)
	public ResponseEntity<CustomExceptionResponse> repositoryException(InternalRepositoryException exception) {
        CustomExceptionResponse exceptionresponse = new CustomExceptionResponse();
        
        exceptionresponse.setErrorMessage("Repository Error.");
        exceptionresponse.setErrors(exception.getErrormessage());
        
        return new ResponseEntity<CustomExceptionResponse>(exceptionresponse, HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	
	
	@ExceptionHandler(UrlNotRepositoryException.class)
	public ResponseEntity<CustomExceptionResponse> urlNotRepositoryException(UrlNotRepositoryException exception) {
        CustomExceptionResponse exceptionresponse = new CustomExceptionResponse();
        
        exceptionresponse.setErrorMessage("Repository Error.");
        exceptionresponse.setErrors(exception.getErrormessage());
        
        return new ResponseEntity<CustomExceptionResponse>(exceptionresponse, HttpStatus.SERVICE_UNAVAILABLE);
	}
}

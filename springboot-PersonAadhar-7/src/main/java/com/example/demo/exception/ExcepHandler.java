package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExcepHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex)
	{
		Map<String, String> errormap=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error->{
			errormap.put(error.getField(),error.getDefaultMessage());
		});
		return errormap;
	}	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex,WebRequest request) 
	{
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(UserNotFoundException.class)
	public Map<String,String> handleBussinessException(UserNotFoundException msg)
	{
		Map<String, String> errMap=new HashMap<>();
		errMap.put("errormessage",msg.getMessage());
		return errMap;
	}
	
}

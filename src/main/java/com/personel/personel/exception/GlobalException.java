package com.personel.personel.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
	@ExceptionHandler(UserDuplicateException.class)
	public ResponseEntity<Map<String,Object>> hadleUserDuplicateException(UserDuplicateException e){
		Map<String, Object> errorDetails=new HashMap<String, Object>();
		errorDetails.put("message", e.getMessage());
		errorDetails.put("status",HttpStatus.CONFLICT);
		errorDetails.put("eror", "Kullanıcı zaten kayıtlı");
		return new ResponseEntity<>(errorDetails,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,Object>> hadleValidationException(MethodArgumentNotValidException e){
		Map<String, Object> errorDetails=new HashMap<String, Object>();
		e.getBindingResult().getAllErrors().forEach(
				(error)->{
					String fieldName=((FieldError) error).getField();
					String errosMesage=error.getDefaultMessage();
					errorDetails.put(fieldName, errosMesage);
				}
				);
		
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}

}

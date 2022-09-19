package com.search.web.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = BindException.class)
	protected ResponseEntity<Object> handleException(BindException exception) {
		BindingResult bindingResult = exception.getBindingResult();

		StringBuilder stringBuilder = new StringBuilder();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			stringBuilder.append(fieldError.getField()).append(":");
			stringBuilder.append(fieldError.getDefaultMessage());
			stringBuilder.append(", ");
		}

		return new ResponseEntity<>(
			new ExceptionResponse(ZonedDateTime.now(), stringBuilder.toString()),
			HttpStatus.BAD_REQUEST
		);
	}
}

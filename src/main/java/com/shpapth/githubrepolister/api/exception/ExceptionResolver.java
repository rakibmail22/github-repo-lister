package com.shpapth.githubrepolister.api.exception;

import com.shpapth.githubrepolister.api.model.ErrorResponse;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionResolver {

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler({InvalidParameterException.class})
	public ErrorResponse handleResourceNotFound(InvalidParameterException ex) {
		return new ErrorResponse(ex.getResourceName(), ex.getMsg());
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getFieldErrors()
		         .stream()
		         .map(fieldError -> new ErrorResponse(fieldError.getField(), fieldError.getDefaultMessage()))
		         .toList();
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleFeignException(FeignException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ex.request().url(), ex.getMessage());
		return ResponseEntity.status(ex.status()).body(errorResponse);
	}
}
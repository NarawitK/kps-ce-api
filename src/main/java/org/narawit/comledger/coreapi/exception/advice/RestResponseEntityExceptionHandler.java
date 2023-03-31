package org.narawit.comledger.coreapi.exception.advice;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.narawit.comledger.coreapi.constant.ExceptionMessageConstants.*;
import org.narawit.comledger.coreapi.contract.BaseResponse;
import org.narawit.comledger.coreapi.exception.RefreshTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = RefreshTokenException.class)
	public BaseResponse<ZonedDateTime> handleTokenRefreshException(RefreshTokenException ex, WebRequest request) {
		return new BaseResponse<ZonedDateTime>(false, ex.getMessage(), ZonedDateTime.now());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public BaseResponse<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
			
		});
		BaseResponse<Map<String, String>> response = new BaseResponse<Map<String,String>>(false, VALIDATION_ERROR_MSG, errors);
		return response;
	}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(ExpiredJwtException.class)
	public BaseResponse<String> handleExpiredAccessToken(ExpiredJwtException e){
		return new BaseResponse<String>(false, e.getMessage(), null);
	}
}

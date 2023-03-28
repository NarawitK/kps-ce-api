package org.narawit.comledger.coreapi.controller.common;

import org.narawit.comledger.coreapi.contract.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

public class ControllerHelper {
	public static <T> ResponseEntity<BaseResponse<T>> getOkResponseEntity(String message, T data){
		BaseResponse<T> response = new BaseResponse<T>(true, message, data);
		return new ResponseEntity<BaseResponse<T>>(response, HttpStatus.OK);
	}
	
	public static <T> ResponseEntity<BaseResponse<T>> getResponseEntity(String message, T data, HttpStatusCode code){
		BaseResponse<T> response = new BaseResponse<T>(true, message, data);
		return new ResponseEntity<BaseResponse<T>>(response, code);
	}
	
	public static <T> ResponseEntity<BaseResponse<T>> getResponseEntity(String message, T data, HttpStatusCode code, boolean success){
		BaseResponse<T> response = new BaseResponse<T>(success, message, data);
		return new ResponseEntity<BaseResponse<T>>(response, code);
	}
	
	
	public static <T> ResponseEntity<BaseResponse<T>> getHandleResponseException(ResponseStatusException ex) {
		BaseResponse<T> response = new BaseResponse<T> (false, ex.getMessage(), null);
		return new ResponseEntity<BaseResponse<T>>(response, ex.getStatusCode());
	}
	
	public static <T> ResponseEntity<BaseResponse<T>> getHandleResponseForException(Exception e, HttpStatusCode status) {
		BaseResponse<T> response = new BaseResponse<T>(false, e.getMessage(), null);
		return new ResponseEntity<BaseResponse<T>>(response, status);
	}
}

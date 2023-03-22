package org.narawit.comledger.coreapi.controller.common;

import org.narawit.comledger.coreapi.contract.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

public class ControllerHelper {
	public static <T> ResponseEntity<BaseResponse<T>> getOkResponseEntity(T data){
		return new ResponseEntity<BaseResponse<T>>(new BaseResponse<T>(data), HttpStatus.OK);
	}
	
	public static <T> ResponseEntity<BaseResponse<T>> getResponseEntity(T data, HttpStatus code){
		return new ResponseEntity<BaseResponse<T>>(new BaseResponse<T>(data), code);
	}
	
	
	public static <T> ResponseEntity<BaseResponse<T>> getHandleResponseException(ResponseStatusException ex) {
		return new ResponseEntity<BaseResponse<T>>(new BaseResponse<T>(null, ex.getMessage()), ex.getStatusCode());
	}
	
	public static <T> ResponseEntity<BaseResponse<T>> getHandleResponseForException(Exception ex, HttpStatus code) {
		return new ResponseEntity<BaseResponse<T>>(new BaseResponse<T>(null, ex.getMessage()), code);
	}
}

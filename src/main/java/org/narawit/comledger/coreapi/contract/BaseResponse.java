package org.narawit.comledger.coreapi.contract;

public record BaseResponse<T>(boolean success, String message, T data){
	
}
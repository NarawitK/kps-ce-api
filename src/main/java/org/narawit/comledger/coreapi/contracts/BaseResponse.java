package org.narawit.comledger.coreapi.contracts;

public record BaseResponse<T>(boolean error, T data, String exception) {
	public BaseResponse(T data){
		this(false, data, null);
	}
	
	public BaseResponse(T data, Exception exception) {
		this(true, data, exception.getMessage());
	}
	
	public BaseResponse(T data, String exceptionMsg) {
		this(true , data, exceptionMsg);
	}
}
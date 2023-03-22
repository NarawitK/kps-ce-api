package org.narawit.comledger.coreapi.contract;

public record BaseResponse<T>(boolean error, String message, T data) {
	public BaseResponse(T data){
		this(false, null, data);
	}
	
	public BaseResponse(T data, Exception exception) {
		this(true, exception.getMessage(), data);
	}
	
	public BaseResponse(T data, String exceptionMsg) {
		this(true , exceptionMsg, data);
	}
}
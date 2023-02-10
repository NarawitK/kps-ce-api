package org.narawit.comledger.coreapi.exceptionhandler;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiError {
	   private HttpStatus status;
	   private ZonedDateTime timestamp;
	   private String message;
	   private String debugMessage;
	   private List<ApiSubError> subErrors;

	   private ApiError() {
	       timestamp = ZonedDateTime.now();
	   }

	   ApiError(HttpStatus status) {
	       this();
	       this.status = status;
	   }

	   ApiError(HttpStatus status, Throwable ex) {
	       this();
	       this.status = status;
	       this.message = "Unexpected error";
	       this.debugMessage = ex.getLocalizedMessage();
	   }

	   ApiError(HttpStatus status, String message, Throwable ex) {
	       this();
	       this.status = status;
	       this.message = message;
	       this.debugMessage = ex.getLocalizedMessage();
	   }
	}
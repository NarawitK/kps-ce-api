package org.narawit.comledger.coreapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AccessTokenExpiredException extends ResponseStatusException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4087627413895091218L;

	public AccessTokenExpiredException(HttpStatus code, String message) {
		super(code, message);
	}
}

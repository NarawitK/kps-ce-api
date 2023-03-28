package org.narawit.comledger.coreapi.contract.auth;

public record AuthenticationContract(String accessToken, String refreshToken, String tokenType) {
	public AuthenticationContract(String accessToken, String refreshToken) {
		this(accessToken, refreshToken, "Bearer");
	}
}
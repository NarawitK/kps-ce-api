package org.narawit.comledger.coreapi.contract.auth;

public record RefreshTokenContract(String accessToken, String refreshToken, String tokenType) {
	public RefreshTokenContract(String accessToken, String refreshToken) {
		this(accessToken, refreshToken, "Bearer");
	}
}
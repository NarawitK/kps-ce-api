package org.narawit.comledger.coreapi.service.token;

import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

public interface BaseJwtTokenService<C> {
	public String generateToken(Map<String, Object> extraClaims, UserDetails user);
	public boolean isTokenValid(String token, UserDetails user);
	public boolean isTokenExpired(String token);
	public C decodeJwt(String token);
	public <T> T extractClaim(String token, Function<C, T> resolver);
}

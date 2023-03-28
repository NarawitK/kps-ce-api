package org.narawit.comledger.coreapi.service.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.SignatureException;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public final class JwtTokenService {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenService.class);
	
	private String issuer;
	private String secret;
	private int tokenExpirationInMs;
	
	@Value("${jwt.issuer}")
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	
	@Value("${jwt.secret}")
	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	@Value("${jwt.expirationDateInMs}")
	public void setTokenExpirationInMs(int ms) {
		this.tokenExpirationInMs = ms;
	}
	
	private Key getSigningKey() {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		byte[] secretBytes = Decoders.BASE64.decode(secret);
		Key signingKey = new SecretKeySpec(secretBytes, signatureAlgorithm.getJcaName());
		return signingKey;
	}
	
	public String generateToken(Map<String, Object> extraClaims, UserDetails user) {
		final String subject = "Access Token";
		Date isa = new Date();
		Date exp = new Date(isa.getTime() + tokenExpirationInMs);
		return Jwts.builder().setHeaderParam("typ", "JWT")
				.setIssuer(issuer)
				.setSubject(subject)
				.setAudience(user.getUsername())
				.setIssuedAt(isa)
				.setNotBefore(isa)
				.setExpiration(exp)
				.signWith(getSigningKey())
				.claim("role", user.getAuthorities().toString())
				.addClaims(extraClaims)
				.compact();
	}
	
	
	public boolean isTokenValid(String token, UserDetails user) {
		final String username = this.extractClaim(token, Claims::getAudience);
		return username.equals(user.getUsername()) && !isTokenExpired(token);
	}
	
	public boolean isTokenExpired(String token) {
		Date expirationDate = this.extractClaim(token, Claims::getExpiration);
		return expirationDate.before(new Date());
	}
	
	public Claims decodeJwt(String token) {
		try {
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(getSigningKey())
					.build()
					.parseClaimsJws(token).getBody();
			return claims;
		} 
		catch (SignatureException e) {
			logger.error("Invalid JWT Signature: {}", e.getMessage());
		}
		catch (MalformedJwtException e) {
			logger.error("Invalid JWT Token: {}", e.getMessage());
		}
		catch (ExpiredJwtException e) {
			logger.error("JWT Token is expired: {}", e.getMessage());
			throw e;
		}
		catch (UnsupportedJwtException e) {
			logger.error("This JWT Token is Unsupported: {}", e.getMessage());
		}
		catch (IllegalArgumentException e) {
			logger.error("JWT token String is empty: {}", e.getMessage());
		}
		return null;
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> resolver) {
		Claims claims = decodeJwt(token);
		return resolver.apply(claims);
	}
}

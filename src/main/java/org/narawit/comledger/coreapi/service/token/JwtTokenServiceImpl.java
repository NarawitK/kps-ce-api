package org.narawit.comledger.coreapi.service.token;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.SignatureException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public final class JwtTokenServiceImpl implements JwtTokenService {

	private static final Logger logger = LoggerFactory.getLogger(JwtTokenServiceImpl.class);
	private String subject;
	private String issuer;
	private String secret;
	private int tokenExpirationInMs;
	
	@Value("${jwt.subject}")
	private void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Value("${jwt.issuer}")
	private void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	
	@Value("${jwt.secret}")
	private void setSecret(String secret) {
		this.secret = secret;
	}
	
	@Value("${jwt.expirationDateInMs}")
	private void setTokenExpirationInMs(int ms) {
		this.tokenExpirationInMs = ms;
	}
	
	private Key getSigningKey() {
		SignatureAlgorithm signature = SignatureAlgorithm.HS256;
		byte[] secretBytes = Decoders.BASE64.decode(secret);
		Key signingKey = new SecretKeySpec(secretBytes, signature.getJcaName());
		return signingKey;
	}
	
	@Override
	public String generateToken(Map<String, Object> extraClaims, UserDetails user) {
		/*
		 * JJWT support only java.util.Date class.
		 * final Instant isa = Instant.now();
		 * final Instant exp = isa.plusMillis(tokenExpirationInMs);
		*/
		
		final Date isa = new Date();
		final Date exp = new Date(isa.getTime() + tokenExpirationInMs);
		final String jwtTypeKey = "typ";
		final String jwtTypeValue = "JWT";
		return Jwts.builder()
				.setHeaderParam(jwtTypeKey, jwtTypeValue)
				.setIssuer(issuer)
				.setSubject(subject)
				.setAudience(user.getUsername())
				.setIssuedAt(isa)
				.setNotBefore(isa)
				.setExpiration(exp)
				.signWith(this.getSigningKey())
				// Only one role per user in this system.
				.claim("role", user.getAuthorities().toString())
				.addClaims(extraClaims)
				.compact();
	}

	@Override
	public boolean isTokenValid(String token, UserDetails user) {
		final String username = this.extractClaim(token, Claims::getAudience);
		return username.equals(user.getUsername()) && !this.isTokenExpired(token);
	}

	@Override
	public boolean isTokenExpired(String token) {
		Date expirationDate = this.extractClaim(token, Claims::getExpiration);
		return expirationDate != null ? expirationDate.before(new Date()) : false;
	}

	@Override
	public Claims decodeJwt(String token) {
		try {
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(this.getSigningKey())
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
		}
		catch (UnsupportedJwtException e) {
			logger.error("This JWT Token is Unsupported: {}", e.getMessage());
		}
		catch (IllegalArgumentException e) {
			logger.error("JWT token String is empty: {}", e.getMessage());
		}
		return null;
	}

	@Override
	public <T> T extractClaim(String token, Function<Claims, T> resolver) {
		Claims claims = this.decodeJwt(token);
		return claims != null ? resolver.apply(claims) : null;
	}
}

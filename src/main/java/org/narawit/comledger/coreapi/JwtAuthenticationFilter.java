package org.narawit.comledger.coreapi;

import java.io.IOException;

import static org.narawit.comledger.coreapi.constant.ExceptionMessageConstants.ACCESS_TOKEN_INVALID_MSG;
import static org.narawit.comledger.coreapi.constant.ExceptionMessageConstants.ACCESS_TOKEN_EXPIRED_OR_INVALID_MSG;
import org.narawit.comledger.coreapi.exception.AccessTokenExpiredException;
import org.narawit.comledger.coreapi.service.token.JwtTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter  {
	private final JwtTokenService jwtService;
	private final UserDetailsService userService;
	
	public JwtAuthenticationFilter(JwtTokenService service, UserDetailsService userService) {
		this.jwtService = service;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		try {
			final String jwt = authHeader.substring(7);
			final String credential = jwtService.extractClaim(jwt, Claims::getAudience);
			
			if(credential != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.userService.loadUserByUsername(credential);
				if(jwtService.isTokenValid(jwt, userDetails)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
				else {
					throw new AccessTokenExpiredException(HttpStatus.UNAUTHORIZED, ACCESS_TOKEN_INVALID_MSG);
				}
			}
			else {
				throw new AccessTokenExpiredException(HttpStatus.UNAUTHORIZED, ACCESS_TOKEN_EXPIRED_OR_INVALID_MSG);
			}
		}
		catch (AccessTokenExpiredException e) {
			response.setContentType("application/json");
			response.setStatus(e.getStatusCode().value());
			response.getWriter().write(e.getMessage());
		}
		finally {
			filterChain.doFilter(request, response);
		}
	}
}
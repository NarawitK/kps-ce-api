package org.narawit.comledger.coreapi.controller.auth;

import org.narawit.comledger.coreapi.contract.UserRequest;
import org.narawit.comledger.coreapi.contract.auth.AuthenticationContract;
import org.narawit.comledger.coreapi.contract.auth.AuthenticationRequest;
import org.narawit.comledger.coreapi.contract.auth.LogoutRequest;
import org.narawit.comledger.coreapi.contract.auth.RefreshTokenContract;
import org.narawit.comledger.coreapi.contract.auth.RefreshTokenRequest;
import org.narawit.comledger.coreapi.domain.RefreshToken;
import org.narawit.comledger.coreapi.domain.User;
import org.narawit.comledger.coreapi.exception.RefreshTokenException;
import org.narawit.comledger.coreapi.service.RefreshTokenService;
import org.narawit.comledger.coreapi.service.UserService;
import org.narawit.comledger.coreapi.service.token.JwtTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;

// TODO: Add @Valid to every controller method that accept request body.

@RestController
@RequestMapping("/auth")
public class AuthenController {	
	private final JwtTokenService jwtService;
	private final RefreshTokenService refreshTokenService;
	private final UserService userService;
	private final AuthenticationManager authManager;
	
	public AuthenController(JwtTokenService jwtService, RefreshTokenService refreshTokenService,  UserService userService, AuthenticationManager authManager) {
		this.jwtService = jwtService;
		this.refreshTokenService = refreshTokenService;
		this.userService = userService;
		this.authManager = authManager;
	}
	
	@PostMapping
	public ResponseEntity<AuthenticationContract> getAccessTokenWithRefreshToken(@Valid @RequestBody AuthenticationRequest req) {
		// TODO: Caught All Authentication Exception then make a proper response.
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(req.username(), req.password()));
			User user = userService.findByUsername(req.username());
			String accessToken = jwtService.generateToken(null, user);
			String refreshToken = refreshTokenService.createRefreshToken(user.getId()).getToken();
			return new ResponseEntity<AuthenticationContract>(new AuthenticationContract(accessToken, refreshToken), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<AuthenticationContract>(new AuthenticationContract(null, null), HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationContract> registerUser(@Valid @RequestBody UserRequest req) {
		User user = userService.addThenReturnEntity(req);
		String accessToken = jwtService.generateToken(null, user);
		String refreshToken = refreshTokenService.createRefreshToken(user.getId()).getToken();
		return new ResponseEntity<AuthenticationContract>(new AuthenticationContract(accessToken, refreshToken), HttpStatus.CREATED);
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<RefreshTokenContract> refreshAccessToken(@Valid @RequestBody RefreshTokenRequest req){
		String requestRefreshToken = req.refreshToken();
		return refreshTokenService.findByToken(requestRefreshToken)
				.map(refreshTokenService::verifyExpiration)
				.map(RefreshToken::getUser)
				.map(user -> {
					String token = jwtService.generateToken(null, user);
					return new ResponseEntity<RefreshTokenContract>(new RefreshTokenContract(token, requestRefreshToken), HttpStatus.CREATED);
				}).orElseThrow(() -> new RefreshTokenException(requestRefreshToken, "Refresh Token not exist in server"));
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestBody LogoutRequest req){
		String accessToken = req.accessToken();
		String uname = jwtService.extractClaim(accessToken, Claims::getSubject);
		Long uid = userService.findByUsername(uname).getId();
		refreshTokenService.deleteByUser(uid);
		return ResponseEntity.noContent().build();
	}
}

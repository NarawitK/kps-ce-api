package org.narawit.comledger.coreapi.service;

import java.util.Optional;

import org.narawit.comledger.coreapi.domain.RefreshToken;

public interface RefreshTokenService {
	Optional<RefreshToken> findByToken(String token);
	RefreshToken createRefreshToken(Long userId);
	RefreshToken verifyExpiration(RefreshToken token);
	Long deleteByUser(Long userId);
}

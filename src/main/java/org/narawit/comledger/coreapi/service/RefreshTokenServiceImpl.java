package org.narawit.comledger.coreapi.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.narawit.comledger.coreapi.domain.RefreshToken;
import org.narawit.comledger.coreapi.domain.User;
import org.narawit.comledger.coreapi.exception.RefreshTokenException;
import org.narawit.comledger.coreapi.repo.RefreshTokenRepo;
import org.narawit.comledger.coreapi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
	@Value("${jwt.refreshExpirationDateInMs}")
	private Long refreshTokenDurationMs;
	
	private final RefreshTokenRepo rtRepo;
	private final UserRepo userRepo;
	
	public RefreshTokenServiceImpl(RefreshTokenRepo rtRepo, UserRepo userRepo) {
		this.rtRepo = rtRepo;
		this.userRepo = userRepo;
	}

	@Override
	public Optional<RefreshToken> findByToken(String token) {
		return rtRepo.findByToken(token);
	}

	@Override
	public RefreshToken createRefreshToken(Long userId) {
		Optional<User> user = userRepo.findById(userId);
		Instant expr = Instant.now().plusMillis(refreshTokenDurationMs);
		String uuid = UUID.randomUUID().toString();
		RefreshToken refreshToken = new RefreshToken(user.get(), uuid, expr);
		return rtRepo.save(refreshToken);
	}

	@Override
	public RefreshToken verifyExpiration(RefreshToken token) {
		if(token.getExpiryDate().compareTo(Instant.now()) < 0) {
			rtRepo.delete(token);
			throw new RefreshTokenException(token.getToken(), "Refresh Token was expired.");
		}
		return token;
	}

	@Override
	@Transactional
	public Long deleteByUser(Long userId) {
		return rtRepo.deleteByUser(userRepo.findById(userId).get());
	}
}

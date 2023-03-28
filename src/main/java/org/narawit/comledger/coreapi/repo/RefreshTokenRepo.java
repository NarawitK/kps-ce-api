package org.narawit.comledger.coreapi.repo;

import java.util.Optional;

import org.narawit.comledger.coreapi.domain.RefreshToken;
import org.narawit.comledger.coreapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {
	Optional<RefreshToken> findByToken(String token);
	
	@Modifying
	long deleteByUser(User user);
}

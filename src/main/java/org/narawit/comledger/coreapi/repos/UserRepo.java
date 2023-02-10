package org.narawit.comledger.coreapi.repos;

import java.util.Optional;

import org.narawit.comledger.coreapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long>{
	User findByEmail(String email);
	Optional<User> findByUsername(String username);
	User findByActive(boolean active);
}

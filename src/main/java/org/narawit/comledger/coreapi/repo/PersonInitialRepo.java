package org.narawit.comledger.coreapi.repo;

import java.util.Optional;

import org.narawit.comledger.coreapi.domain.PersonInitial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonInitialRepo extends JpaRepository<PersonInitial, Long> {
	boolean existsByInitial(String initial);
	Optional<PersonInitial> findByInitial(String initial);
}

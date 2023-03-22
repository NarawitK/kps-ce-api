package org.narawit.comledger.coreapi.repo.option;

import org.narawit.comledger.coreapi.domain.option.CdromType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CdromTypeRepo extends JpaRepository<CdromType, Integer>{
	boolean existsByType(String type);
}

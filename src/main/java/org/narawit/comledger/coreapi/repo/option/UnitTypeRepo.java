package org.narawit.comledger.coreapi.repo.option;

import org.narawit.comledger.coreapi.domain.option.UnitType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitTypeRepo extends JpaRepository<UnitType, Integer> {
	public boolean existsByType(String type);
}

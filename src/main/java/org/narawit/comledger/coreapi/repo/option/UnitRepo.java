package org.narawit.comledger.coreapi.repo.option;

import org.narawit.comledger.coreapi.domain.option.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepo extends JpaRepository<Unit, Integer> {
	public boolean existsByUnitAndUnitTypeId(String unit, Integer id);
}
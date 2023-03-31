package org.narawit.comledger.coreapi.repo.option;

import org.narawit.comledger.coreapi.domain.option.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UnitRepo extends JpaRepository<Unit, Integer> {
	public boolean existsByUnitAndUnitTypeId(String unit, Integer id);
	
	@Query("SELECT u FROM Unit u INNER JOIN u.unitType AS ut WHERE LOWER(ut.type) = LOWER(?1)")
	Iterable<Unit> findByUnitTypeType(String type);
}
package org.narawit.comledger.coreapi.repo.equipment;

import org.narawit.comledger.coreapi.domain.equipment.Ups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpsRepo extends JpaRepository<Ups, Long> {
	public boolean existsByEquipmentId(Long id);
}

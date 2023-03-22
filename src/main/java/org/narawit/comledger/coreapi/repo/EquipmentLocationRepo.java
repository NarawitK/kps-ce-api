package org.narawit.comledger.coreapi.repo;

import org.narawit.comledger.coreapi.domain.EquipmentLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentLocationRepo extends JpaRepository<EquipmentLocation, Long> {
	public boolean existsByEquipmentId(Long equipmentId);
}

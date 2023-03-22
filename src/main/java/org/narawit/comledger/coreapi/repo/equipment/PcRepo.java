package org.narawit.comledger.coreapi.repo.equipment;

import org.narawit.comledger.coreapi.domain.equipment.Pc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PcRepo extends JpaRepository<Pc, Long> {
	public boolean existsByEquipmentId(Long id);
}

package org.narawit.comledger.coreapi.repo.equipment;

import org.narawit.comledger.coreapi.domain.equipment.Nic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NicRepo extends JpaRepository<Nic, Long> {
	boolean existsByEquipmentId(Long id);
}

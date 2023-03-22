package org.narawit.comledger.coreapi.repo;

import org.narawit.comledger.coreapi.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepo extends JpaRepository<Equipment, Long> {
	boolean existByName(String name);
}

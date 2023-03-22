package org.narawit.comledger.coreapi.repo.equipment;

import org.narawit.comledger.coreapi.domain.equipment.Scanner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScannerRepo extends JpaRepository<Scanner, Long> {
	public boolean existsByEquipmentId(Long id);
}

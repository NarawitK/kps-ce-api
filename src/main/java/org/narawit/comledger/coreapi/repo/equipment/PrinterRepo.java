package org.narawit.comledger.coreapi.repo.equipment;

import org.narawit.comledger.coreapi.domain.equipment.Printer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrinterRepo extends JpaRepository<Printer, Long> {
	public boolean existsByEquipmentId(Long id);
}

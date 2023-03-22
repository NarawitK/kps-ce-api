package org.narawit.comledger.coreapi.repo.equipment;

import org.narawit.comledger.coreapi.domain.equipment.Vga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VgaRepo extends JpaRepository<Vga, Long> {
 public boolean existsByEquipmentId(Long id);
}

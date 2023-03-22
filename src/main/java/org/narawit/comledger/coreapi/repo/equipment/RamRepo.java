package org.narawit.comledger.coreapi.repo.equipment;

import org.narawit.comledger.coreapi.domain.equipment.Ram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RamRepo extends JpaRepository<Ram, Long> {
	public boolean existsByModel(String model);
}

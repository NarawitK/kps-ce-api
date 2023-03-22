package org.narawit.comledger.coreapi.repo.option;

import org.narawit.comledger.coreapi.domain.option.PrinterType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrinterTypeRepo extends JpaRepository<PrinterType, Integer>{
	public boolean existsByType(String type);
}

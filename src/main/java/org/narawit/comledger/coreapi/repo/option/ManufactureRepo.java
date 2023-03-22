package org.narawit.comledger.coreapi.repo.option;

import org.narawit.comledger.coreapi.domain.option.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufactureRepo extends JpaRepository<Manufacture, Integer> {
	public boolean existsByName(String name);
}

package org.narawit.comledger.coreapi.repo.option;

import org.narawit.comledger.coreapi.domain.option.ManufactureCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufactureCategoryRepo extends JpaRepository<ManufactureCategory, Integer>{
	public boolean existsByCategory(String category);
}
package org.narawit.comledger.coreapi.repo;

import org.narawit.comledger.coreapi.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
	public boolean existsByName(String name);
}

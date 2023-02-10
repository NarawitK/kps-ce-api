package org.narawit.comledger.coreapi.repos;

import org.narawit.comledger.coreapi.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
	
}

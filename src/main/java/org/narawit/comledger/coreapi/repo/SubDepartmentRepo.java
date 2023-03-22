package org.narawit.comledger.coreapi.repo;

import org.narawit.comledger.coreapi.domain.SubDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;

@Transactional
public interface SubDepartmentRepo extends JpaRepository<SubDepartment, Integer>{
	boolean existsByName(String name);
}

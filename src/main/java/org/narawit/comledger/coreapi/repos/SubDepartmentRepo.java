package org.narawit.comledger.coreapi.repos;

import org.narawit.comledger.coreapi.domain.SubDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;

@Transactional
public interface SubDepartmentRepo extends JpaRepository<SubDepartment, Integer>{

}

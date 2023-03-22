package org.narawit.comledger.coreapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.narawit.comledger.coreapi.contract.DepartmentContract;
import org.narawit.comledger.coreapi.contract.DepartmentRequest;
import org.narawit.comledger.coreapi.domain.Department;
import org.narawit.comledger.coreapi.repo.DepartmentRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentRepo repo;
	
	public DepartmentServiceImpl(DepartmentRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public Iterable<DepartmentContract> findAll() {
		Iterable<Department> resultset = repo.findAll();
		List<DepartmentContract> contracts = new ArrayList<>();
		if(ServiceCommon.IsIterableHasElements(resultset)) {
			for (Department department : resultset) {
				contracts.add(new DepartmentContract(department));
			}
		}
		return contracts;
	}
	
	@Override
	public DepartmentContract findById(Integer identity) {
		Optional<Department> result = repo.findById(identity);
		if(result.isPresent()) {
			return new DepartmentContract(result.get());
		}
		return null;
	}

	@Override
	@Transactional
	public DepartmentContract add(DepartmentRequest entity) {
		if(!repo.existsByName(entity.name())) {
			Department persistDept = new Department(entity);
			persistDept = repo.save(persistDept);
			return new DepartmentContract(persistDept);			
		}
		else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Department name already exist");
		}
	}

	@Override
	@Transactional
	public DepartmentContract edit(Integer identity, DepartmentRequest entity) throws ResponseStatusException {
		if(repo.existsById(identity)) {
			Department persistDept = new Department(identity, entity);
			persistDept = repo.save(persistDept);
			return new DepartmentContract(persistDept);
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department is not exist");
		}
	}
	
	@Override
	@Transactional
	public void remove(Integer identity) {
		repo.deleteById(identity);
	}
}
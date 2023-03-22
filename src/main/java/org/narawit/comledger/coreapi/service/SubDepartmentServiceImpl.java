package org.narawit.comledger.coreapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.narawit.comledger.coreapi.contract.SubDepartmentContract;
import org.narawit.comledger.coreapi.contract.SubDepartmentRequest;
import org.narawit.comledger.coreapi.domain.SubDepartment;
import org.narawit.comledger.coreapi.repo.SubDepartmentRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.narawit.comledger.coreapi.utilities.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class SubDepartmentServiceImpl implements SubDepartmentService {
	private final SubDepartmentRepo repo;
	
	public SubDepartmentServiceImpl(SubDepartmentRepo repo) {
		this.repo = repo;
	}

	@Override
	public Iterable<SubDepartmentContract> findAll() {
		Iterable<SubDepartment> rs = repo.findAll();
		List<SubDepartmentContract> contracts = new ArrayList<>();
		if(ServiceCommon.IsIterableHasElements(rs)) {
			for(SubDepartment subDept : rs) {
				contracts.add(new SubDepartmentContract(subDept));
			}
		}
		return contracts;
	}

	@Override
	public SubDepartmentContract findById(Integer identity) {
		Optional<SubDepartment> rs = repo.findById(identity);
		if(rs.isPresent())
			return new SubDepartmentContract(rs.get());
		return null;
	}

	@Override
	@Transactional
	public SubDepartmentContract add(SubDepartmentRequest req) throws ResponseStatusException {
		if(StringUtils.isStringNotEmpty(req.name())) {
			if(!repo.existsByName(req.name())) {
				SubDepartment persisted = repo.save(new SubDepartment(req));
				return new SubDepartmentContract(persisted);
			}
			else {
				throw new ResponseStatusException(HttpStatus.CONFLICT, "SubDepartment with this name already exist");
			}
			
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is empty");
		}
		
	}

	@Override
	@Transactional
	public SubDepartmentContract edit(Integer identity, SubDepartmentRequest req) throws ResponseStatusException {
		if(repo.existsById(identity)) {
			SubDepartment persist = repo.save(new SubDepartment(identity, req));
			return new SubDepartmentContract(persist);
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "SubDepartment does not exist");
	}

	@Override
	@Transactional
	public void remove(Integer identity) {
		repo.deleteById(identity);			
	}
}

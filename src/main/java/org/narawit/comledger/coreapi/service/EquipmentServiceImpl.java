package org.narawit.comledger.coreapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.narawit.comledger.coreapi.contract.EquipmentContract;
import org.narawit.comledger.coreapi.contract.EquipmentRequest;
import org.narawit.comledger.coreapi.domain.Equipment;
import org.narawit.comledger.coreapi.repo.EquipmentRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EquipmentServiceImpl implements EquipmentService {

	private EquipmentRepo repo;
	
	public EquipmentServiceImpl(EquipmentRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public Iterable<EquipmentContract> findAll() {
		Iterable<Equipment> resultset = repo.findAll();
		List<EquipmentContract> contracts = new ArrayList<>();
		if(ServiceCommon.IsIterableHasElements(resultset)) {
			for (Equipment equipment : resultset) {
				contracts.add(new EquipmentContract(equipment));
			}
		}
		return contracts;
	}

	@Override
	public EquipmentContract findById(Long identity) {
		Optional<Equipment> result = repo.findById(identity);
		if(result.isPresent()) {
			return new EquipmentContract(result.get());
		}
		return null;
	}

	@Override
	public EquipmentContract add(EquipmentRequest entity) throws ResponseStatusException {
		if(!repo.existByName(entity.name())) {
			Equipment persistDept = new Equipment(entity);
			persistDept = repo.save(persistDept);
			return new EquipmentContract(persistDept);			
		}
		else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Equipment with this name already exist");
		}
	}

	@Override
	public EquipmentContract edit(Long identity, EquipmentRequest entity) throws ResponseStatusException {
		boolean isExist = repo.existsById(identity);
		if(isExist) {
			Equipment persistDept = new Equipment(entity);
			persistDept = repo.save(persistDept);
			return new EquipmentContract(persistDept);
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Equipment not exist");
	}

	@Override
	public void remove(Long identity) {
		repo.deleteById(identity);
	}

}

package org.narawit.comledger.coreapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.narawit.comledger.coreapi.contract.EquipmentLocationContract;
import org.narawit.comledger.coreapi.contract.EquipmentLocationRequest;
import org.narawit.comledger.coreapi.domain.EquipmentLocation;
import org.narawit.comledger.coreapi.repo.EquipmentLocationRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class EquipmentLocationServiceImpl implements EquipmentLocationService{
	
	private final EquipmentLocationRepo repo;
	
	public EquipmentLocationServiceImpl(EquipmentLocationRepo repo) {
		this.repo = repo;
	}
	@Override
	public Iterable<EquipmentLocationContract> findAll() {
		Iterable<EquipmentLocation> rs = repo.findAll();
		List<EquipmentLocationContract> contracts = new ArrayList<>();
		if(ServiceCommon.IsIterableHasElements(rs)) {
			for(EquipmentLocation loc : rs) {
				contracts.add(new EquipmentLocationContract(loc));
			}
		}
		return contracts;
	}

	@Override
	public EquipmentLocationContract findById(Long identity) {
		Optional<EquipmentLocation> rs = repo.findById(identity);
		if(rs.isPresent()) {
			return new EquipmentLocationContract(rs.get());
		}
		return null;
	}

	@Override
	@Transactional
	public EquipmentLocationContract add(EquipmentLocationRequest req) throws ResponseStatusException {
		if(!repo.existsByEquipmentId(req.equipmentId())) {
			EquipmentLocation persisted = new EquipmentLocation(req);
			persisted = repo.save(persisted);
			return new EquipmentLocationContract(persisted);			
		}
		else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "This equipment location already exist");
		}
	}
	@Override
	@Transactional
	public EquipmentLocationContract edit(Long identity, EquipmentLocationRequest req) throws ResponseStatusException {
		boolean isExist = repo.existsById(identity);
		if(isExist) {
			EquipmentLocation persisted = new EquipmentLocation(req);
			persisted = repo.save(persisted);
			return new EquipmentLocationContract(persisted);			
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This equipment location is not exist");
		}
	}
	@Override
	@Transactional
	public void remove(Long identity){
		repo.deleteById(identity);
	}
}

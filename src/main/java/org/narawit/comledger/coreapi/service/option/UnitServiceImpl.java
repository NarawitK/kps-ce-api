package org.narawit.comledger.coreapi.service.option;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.narawit.comledger.coreapi.contract.option.UnitContract;
import org.narawit.comledger.coreapi.contract.option.UnitRequest;
import org.narawit.comledger.coreapi.domain.option.Unit;
import org.narawit.comledger.coreapi.repo.option.UnitRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UnitServiceImpl implements UnitService{
	private final UnitRepo repo;
	
	public UnitServiceImpl(UnitRepo repo) {
		this.repo = repo;
	}

	@Override
	public Iterable<UnitContract> findAll() {
		Iterable<Unit> rs = repo.findAll();
		List<UnitContract> contracts = new ArrayList<>();
		if(ServiceCommon.IsIterableHasElements(rs)) {
			for(Unit u : rs) {
				contracts.add(new UnitContract(u));
			}
		}
		return contracts;
	}

	@Override
	public UnitContract findById(Integer identity) {
		Optional<Unit> result = repo.findById(identity);
		if(result.isPresent()) {
			return new UnitContract(result.get());
		}
		return null;
	}

	@Override
	public UnitContract add(UnitRequest req) throws ResponseStatusException {
		boolean isExist = repo.existsByUnitAndUnitTypeId(req.unit(), req.unitTypeId());
		if(!isExist) {
			Unit persisted = repo.save(new Unit(req));
			return new UnitContract(persisted);
		}
		else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate Unit Entry");
		}
	}

	@Override
	public UnitContract edit(Integer identity, UnitRequest req) throws ResponseStatusException {
		boolean isExist = repo.existsById(identity);
		if(isExist) {
			Unit persisted = repo.save(new Unit(identity, req));
			return new UnitContract(persisted);
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unit does not exist");
		}
	}

	@Override
	public void remove(Integer identity) {
		repo.deleteById(identity);
	}
}

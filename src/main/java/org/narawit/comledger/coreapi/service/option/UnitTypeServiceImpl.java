package org.narawit.comledger.coreapi.service.option;

import java.util.List;
import java.util.Optional;
import org.narawit.comledger.coreapi.contract.option.UnitTypeContract;
import org.narawit.comledger.coreapi.contract.option.UnitTypeRequest;
import org.narawit.comledger.coreapi.domain.option.UnitType;
import org.narawit.comledger.coreapi.repo.option.UnitTypeRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UnitTypeServiceImpl implements UnitTypeService {
	private final UnitTypeRepo repo;
	
	public UnitTypeServiceImpl(UnitTypeRepo repo) {
		this.repo = repo;
	}
	@Override
	public Iterable<UnitTypeContract> findAll() {
		Iterable<UnitType> rs = repo.findAll();
		//contracts = new ArrayList<>();
		List<UnitTypeContract> contracts = ServiceCommon.AddEntityToContractCollection(rs, (e) ->{
			return new UnitTypeContract(e);
		});
		return contracts;
	}

	@Override
	public UnitTypeContract findById(Integer identity) {
		Optional<UnitType> result = repo.findById(identity);
		if(result.isPresent()) {
			return new UnitTypeContract(result.get());
		}
		return null;
	}

	@Override
	public UnitTypeContract add(UnitTypeRequest req) throws ResponseStatusException {
		if(!repo.existsByType(req.type())) {
			UnitType persisted = repo.save(new UnitType(req));
			return new UnitTypeContract(persisted);
		}
		else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate UnitType");
		}
	}

	@Override
	public UnitTypeContract edit(Integer identity, UnitTypeRequest req) throws ResponseStatusException {
		if(repo.existsById(identity)) {
			UnitType persisted = repo.save(new UnitType(req));
			return new UnitTypeContract(persisted);
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UnitType does not exist");
		}
	}

	@Override
	public void remove(Integer identity) {
		repo.deleteById(identity);
	}

}

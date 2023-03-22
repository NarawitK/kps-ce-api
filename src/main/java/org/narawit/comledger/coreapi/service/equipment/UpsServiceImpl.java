package org.narawit.comledger.coreapi.service.equipment;

import java.util.List;
import java.util.Optional;

import org.narawit.comledger.coreapi.contracts.equipment.UpsContract;
import org.narawit.comledger.coreapi.contracts.equipment.UpsRequest;
import org.narawit.comledger.coreapi.domain.equipment.Ups;
import org.narawit.comledger.coreapi.repo.equipment.UpsRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpsServiceImpl implements UpsService {
	
	private final UpsRepo repo;
	
	public UpsServiceImpl(UpsRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public Iterable<UpsContract> findAll() {
		Iterable<Ups> result = repo.findAll();
		List<UpsContract> contracts = ServiceCommon.AddEntityToContractCollection(result, e ->{
			return new UpsContract(e);
		});
		return contracts;
	}

	@Override
	public UpsContract findById(Long identity) {
		Optional<Ups> result = repo.findById(identity);
		return result.isPresent() ? new UpsContract(result.get()) : null;
	}

	@Override
	public UpsContract add(UpsRequest req) throws ResponseStatusException {
		if(!repo.existsByEquipmentId(req.equipmentId())) {
			return persistToDb(req);			
		}
		else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "UPS already exist (Same Equipment ID)");
		}
	}

	@Override
	public UpsContract edit(Long identity, UpsRequest req) throws ResponseStatusException {
		if(repo.existsById(identity))
			return persistToDb(req);
		else
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This UPS does not exist");
	}

	@Override
	public void remove(Long identity){
		repo.deleteById(identity);
	}
	
	private UpsContract persistToDb(UpsRequest req) {
		Ups persisted = repo.save(new Ups(req));
		return new UpsContract(persisted);
	}

}

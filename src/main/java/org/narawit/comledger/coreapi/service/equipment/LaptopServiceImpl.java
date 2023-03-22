package org.narawit.comledger.coreapi.service.equipment;

import java.util.Optional;

import org.narawit.comledger.coreapi.contracts.equipment.LaptopContract;
import org.narawit.comledger.coreapi.contracts.equipment.LaptopRequest;
import org.narawit.comledger.coreapi.domain.equipment.Laptop;
import org.narawit.comledger.coreapi.repo.equipment.LaptopRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LaptopServiceImpl implements LaptopService {
	
	private final LaptopRepo repo;
	
	public LaptopServiceImpl(LaptopRepo repo) {
		this.repo = repo;
	}

	@Override
	public Iterable<LaptopContract> findAll() {
		Iterable<Laptop> resultSet = repo.findAll();
		return ServiceCommon.AddEntityToContractCollection(resultSet, e -> {
			return new LaptopContract(e);
		});
	}

	@Override
	public LaptopContract findById(Long identity) {
		Optional<Laptop> result = repo.findById(identity);
		return result.isPresent() ? new LaptopContract(result.get()) : null;
	}

	@Override
	public LaptopContract add(LaptopRequest req) throws ResponseStatusException {
		if(!repo.existsByEquipmentId(req.equipmentId())) {
			return this.persistToDb(null, req);			
		}
		else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "This Laptop with this equipment ID already exist");
		}
	}

	@Override
	public LaptopContract edit(Long identity, LaptopRequest req) throws ResponseStatusException {
		if(repo.existsById(identity)) {
			return this.persistToDb(identity, req);
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Laptop not exist");
		}
	}

	@Override
	public void remove(Long identity){
		repo.deleteById(identity);
	}
	
	private LaptopContract persistToDb(Long id, LaptopRequest req) {
		if(id == null) {
			Laptop persisted = repo.save(new Laptop(req));
			return new LaptopContract(persisted);
		}
		else {
			Laptop persisted = repo.save(new Laptop(id, req));
			return new LaptopContract(persisted);
		}
	}
}

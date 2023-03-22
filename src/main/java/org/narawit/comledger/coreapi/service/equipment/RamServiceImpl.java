package org.narawit.comledger.coreapi.service.equipment;

import java.util.List;
import java.util.Optional;

import org.narawit.comledger.coreapi.contracts.equipment.RamContract;
import org.narawit.comledger.coreapi.contracts.equipment.RamRequest;
import org.narawit.comledger.coreapi.domain.equipment.Ram;
import org.narawit.comledger.coreapi.repo.equipment.RamRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class RamServiceImpl implements RamService {
	
	private final RamRepo repo;
	
	public RamServiceImpl(RamRepo repo) {
		this.repo = repo;
	}

	@Override
	public Iterable<RamContract> findAll() {
		Iterable<Ram> result = repo.findAll();
		List<RamContract> contracts = ServiceCommon.AddEntityToContractCollection(result, e -> { 
			return new RamContract(e);
		});
		return contracts;
	}

	@Override
	public RamContract findById(Long identity) {
		Optional<Ram> result = repo.findById(identity);
		return result.isPresent() ? new RamContract(result.get()) : null;
	}

	@Override
	@Transactional
	public RamContract add(RamRequest req) throws ResponseStatusException {
		if(!repo.existsByModel(req.model())) {
			return persistToDb(req);			
		}
		else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Ram with this model already exist");
		}
	}

	@Override
	@Transactional
	public RamContract edit(Long identity, RamRequest req) throws ResponseStatusException {
		if(repo.existsById(identity)) {
			return persistToDb(req);
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This RAM does not exist");
		}
	}

	@Override
	@Transactional
	public void remove(Long identity) {
		repo.deleteById(identity);
	}
	
	private RamContract persistToDb(RamRequest req) {
		Ram persisted = repo.save(new Ram(req));
		return new RamContract(persisted);
	}

}

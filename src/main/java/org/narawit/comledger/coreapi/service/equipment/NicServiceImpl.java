package org.narawit.comledger.coreapi.service.equipment;

import java.util.List;
import java.util.Optional;

import org.narawit.comledger.coreapi.contracts.equipment.NicContract;
import org.narawit.comledger.coreapi.contracts.equipment.NicRequest;
import org.narawit.comledger.coreapi.domain.equipment.Nic;
import org.narawit.comledger.coreapi.repo.equipment.NicRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class NicServiceImpl implements NicService {
	
	private final NicRepo repo;
	
	public NicServiceImpl(NicRepo repo) {
		this.repo = repo;
	}

	@Override
	public Iterable<NicContract> findAll() {
		Iterable<Nic> result = repo.findAll();
		List<NicContract> contracts = ServiceCommon.AddEntityToContractCollection(result, e -> {
			return new NicContract(e);
		});
		return contracts;
	}

	@Override
	public NicContract findById(Long identity) {
		Optional<Nic> result = repo.findById(identity);
		return result.isPresent() ? new NicContract(result.get()) : null;
	}

	@Override
	@Transactional
	public NicContract add(NicRequest req) throws ResponseStatusException {
		if(!repo.existsByEquipmentId(req.equipmentId())) {
			return persistToDb(req);			
		}
		else {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "NIC already exist with the same equipment ID");
		}
	}

	@Override
	@Transactional
	public NicContract edit(Long identity, NicRequest req) throws ResponseStatusException {
		if(repo.existsById(identity))
			return persistToDb(req);
		else
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This NIC does not exist");
	}

	@Override
	@Transactional
	public void remove(Long identity){
		repo.deleteById(identity);
	}
	
	private NicContract persistToDb(NicRequest req) {
		Nic persisted = repo.save(new Nic(req));
		return new NicContract(persisted);
	}
}

package org.narawit.comledger.coreapi.service.equipment;

import java.util.List;
import java.util.Optional;

import org.narawit.comledger.coreapi.contracts.equipment.PcContract;
import org.narawit.comledger.coreapi.contracts.equipment.PcRequest;
import org.narawit.comledger.coreapi.domain.equipment.Pc;
import org.narawit.comledger.coreapi.repo.equipment.PcRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class PcServiceImpl implements PcService {

	private final PcRepo repo;
	
	public PcServiceImpl(PcRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public Iterable<PcContract> findAll() {
		Iterable<Pc> resultSet = repo.findAll();
		List<PcContract> contracts = ServiceCommon.AddEntityToContractCollection(resultSet, e -> {
			return new PcContract(e);
		});
		return contracts;
	}

	@Override
	public PcContract findById(Long identity) {
		Optional<Pc> result = repo.findById(identity);
		return result.isPresent() ? new PcContract(result.get()) : null;
	}

	@Override
	@Transactional
	public PcContract add(PcRequest req) throws ResponseStatusException {
		if(!repo.existsByEquipmentId(req.equipmentId())) {
			return this.persistToDb(null, req);			
		}
		else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "This PC already exist (Same Equipment ID)");
		}
	}

	@Override
	@Transactional
	public PcContract edit(Long identity, PcRequest req) throws ResponseStatusException {
		if(repo.existsById(identity)) {
			return persistToDb(identity, req);
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This PC is not exist. Cannot update non-exisitence PC");
		}
	}

	@Override
	@Transactional
	public void remove(Long identity) {
		repo.deleteById(identity);
	}
	
	private PcContract persistToDb(Long id ,PcRequest pc) {
		if(id == null) {
			Pc persisted = repo.save(new Pc(id, pc));
			return new PcContract(persisted);
		}
		else {
			Pc persisted = repo.save(new Pc(pc));
			return new PcContract(persisted);			
		}
	}

}

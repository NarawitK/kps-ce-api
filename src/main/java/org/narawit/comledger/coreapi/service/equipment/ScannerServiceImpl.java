package org.narawit.comledger.coreapi.service.equipment;

import java.util.List;
import java.util.Optional;

import org.narawit.comledger.coreapi.contracts.equipment.ScannerContract;
import org.narawit.comledger.coreapi.contracts.equipment.ScannerRequest;
import org.narawit.comledger.coreapi.domain.equipment.Scanner;
import org.narawit.comledger.coreapi.repo.equipment.ScannerRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class ScannerServiceImpl implements ScannerService {
	private final ScannerRepo repo;
	
	public ScannerServiceImpl(ScannerRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public Iterable<ScannerContract> findAll() {
		Iterable<Scanner> result = repo.findAll();
		List<ScannerContract> contracts = ServiceCommon.AddEntityToContractCollection(result, e -> {
			return new ScannerContract(e);
		});
		return contracts;
	}

	@Override
	public ScannerContract findById(Long identity) {
		Optional<Scanner> result = repo.findById(identity);
		return result.isPresent() ? new ScannerContract(result.get()) : null;
	}

	@Override
	@Transactional
	public ScannerContract add(ScannerRequest req) throws ResponseStatusException {
		if(!repo.existsByEquipmentId(req.equipmentId())) {
			return persistToDb(req);			
		}
		else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Scanner with this equipment ID already exist");
		}
	}

	@Override
	@Transactional
	public ScannerContract edit(Long identity, ScannerRequest req) throws ResponseStatusException {
		if(repo.existsById(identity)) {
			return persistToDb(req);
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Scanner does not exist");
		}
	}

	@Override
	@Transactional
	public void remove(Long identity) {
		repo.deleteById(identity);
	}
	
	private ScannerContract persistToDb(ScannerRequest req) {
		Scanner persisted = repo.save(new Scanner(req));
		return new ScannerContract(persisted);
	}
}

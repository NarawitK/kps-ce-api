package org.narawit.comledger.coreapi.service.equipment;

import java.util.List;
import java.util.Optional;

import org.narawit.comledger.coreapi.contracts.equipment.CpuContract;
import org.narawit.comledger.coreapi.contracts.equipment.CpuRequest;
import org.narawit.comledger.coreapi.domain.equipment.Cpu;
import org.narawit.comledger.coreapi.repo.equipment.CpuRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CpuServiceImpl implements CpuService {
	private final CpuRepo repo;
	public CpuServiceImpl(CpuRepo repo) {
		this.repo = repo;
	}
	@Override
	public Iterable<CpuContract> findAll() {
		Iterable<Cpu> result = repo.findAll();
		List<CpuContract> contracts = ServiceCommon.AddEntityToContractCollection(result, e -> {
			return new CpuContract(e);
		});
		return contracts;
	}
	@Override
	public CpuContract findById(Long identity) {
		Optional<Cpu> result = repo.findById(identity);
		return result.isPresent() ? new CpuContract(result.get()) : null; 
	}
	@Override
	public CpuContract add(CpuRequest req) throws ResponseStatusException {
		try {
			return persistToDb(req);			
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	@Override
	public CpuContract edit(Long identity, CpuRequest req) throws ResponseStatusException {
		if(repo.existsById(identity)) {
			return persistToDb(req);
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This CPU does not exist");
		}
	}
	@Override
	public void remove(Long identity){
		repo.deleteById(identity);
	}
	
	private CpuContract persistToDb(CpuRequest req) {
		Cpu persisted = repo.save(new Cpu(req));
		return new CpuContract(persisted);
	}
}

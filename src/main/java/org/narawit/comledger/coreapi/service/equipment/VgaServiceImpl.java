package org.narawit.comledger.coreapi.service.equipment;

import java.util.List;
import java.util.Optional;

import org.narawit.comledger.coreapi.contracts.equipment.VgaContract;
import org.narawit.comledger.coreapi.contracts.equipment.VgaRequest;
import org.narawit.comledger.coreapi.domain.equipment.Vga;
import org.narawit.comledger.coreapi.repo.equipment.VgaRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VgaServiceImpl implements VgaService{
	
	private final VgaRepo repo;
	
	public VgaServiceImpl(VgaRepo repo) {
		this.repo = repo;
	}

	@Override
	public Iterable<VgaContract> findAll() {
		Iterable<Vga> result = repo.findAll();
		List<VgaContract> contracts = ServiceCommon.AddEntityToContractCollection(result, e -> {
			return new VgaContract(e);
		});
		return contracts;
	}

	@Override
	public VgaContract findById(Long identity) {
		Optional<Vga> result = repo.findById(identity);
		return result.isPresent() ? new VgaContract(result.get()) : null;
	}

	@Override
	public VgaContract add(VgaRequest req) throws ResponseStatusException {
		if(!repo.existsByEquipmentId(req.equipmentId())) {
			return persistToDb(null, req);			
		}
		else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "VGA already exist (Same Equipment ID)");
		}
	}

	@Override
	public VgaContract edit(Long identity, VgaRequest req) throws ResponseStatusException {
		if(repo.existsById(identity))
			return persistToDb(identity, req);
		else
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This VGA does not exist");
	}

	@Override
	public void remove(Long identity) {
		repo.deleteById(identity);
	}
	
	private VgaContract persistToDb(Long id, VgaRequest req) {
		Vga persisted = null;
		if(id != null) {
			persisted = repo.save(new Vga(id, req));			
		}
		else {
			persisted = repo.save(new Vga(req));
		}
		return new VgaContract(persisted);
	}
}

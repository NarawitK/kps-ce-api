package org.narawit.comledger.coreapi.service.option;

import java.util.List;
import java.util.Optional;

import org.narawit.comledger.coreapi.contract.option.ManufactureContract;
import org.narawit.comledger.coreapi.contract.option.ManufactureRequest;
import org.narawit.comledger.coreapi.domain.option.Manufacture;
import org.narawit.comledger.coreapi.repo.option.ManufactureRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ManufactureServiceImpl implements ManufactureService{
	
	private final ManufactureRepo repo;
	public ManufactureServiceImpl(ManufactureRepo repo) {
		this.repo = repo;
	}

	@Override
	public Iterable<ManufactureContract> findAll() {
		Iterable<Manufacture> resultset = repo.findAll();
		List<ManufactureContract> contracts = ServiceCommon.AddEntityToContractCollection(resultset, e -> {
			return new ManufactureContract(e);
		});
		return contracts;
	}

	@Override
	public ManufactureContract findById(Integer identity) {
		Optional<Manufacture> result = repo.findById(identity);
		if(result.isPresent())
			return new ManufactureContract(result.get());
		return null;
	}

	@Override
	public ManufactureContract add(ManufactureRequest req) throws ResponseStatusException {
		if(!repo.existsByName(req.name())) {
			Manufacture persisted = repo.save(new Manufacture(req));
			return new ManufactureContract(persisted);			
		}
		else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "This manufacture already exist");
		}
	}

	@Override
	public ManufactureContract edit(Integer identity, ManufactureRequest req) throws ResponseStatusException {
		if(repo.existsById(identity)) {
			Manufacture persisted = repo.save(new Manufacture(req));
			return new ManufactureContract(persisted);
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to edit this manufacture");
		}
	}

	@Override
	public void remove(Integer identity) {
		repo.deleteById(identity);
	}

}

package org.narawit.comledger.coreapi.service.option;

import java.util.List;
import java.util.Optional;

import org.narawit.comledger.coreapi.contract.option.ManufactureCatContract;
import org.narawit.comledger.coreapi.contract.option.ManufactureCatRequest;
import org.narawit.comledger.coreapi.domain.option.ManufactureCategory;
import org.narawit.comledger.coreapi.repo.option.ManufactureCategoryRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ManufactureCatServiceImpl implements ManufactureCatService {
	
	private final ManufactureCategoryRepo repo;
	
	public ManufactureCatServiceImpl(ManufactureCategoryRepo repo) {
		this.repo = repo;
	}

	@Override
	public Iterable<ManufactureCatContract> findAll() {
		Iterable<ManufactureCategory> rs = repo.findAll();
		List<ManufactureCatContract> contracts = ServiceCommon.AddEntityToContractCollection(rs, e -> {
			return new ManufactureCatContract(e);
		});
		return contracts;
	}

	@Override
	public ManufactureCatContract findById(Integer identity) {
		Optional<ManufactureCategory> rs = repo.findById(identity);
		if(rs.isPresent()) {
			return new ManufactureCatContract(rs.get());
		}
		return null;
	}

	@Override
	public ManufactureCatContract add(ManufactureCatRequest req) throws ResponseStatusException {
		boolean isExist = repo.existsByCategory(req.category());
		if(!isExist) {
			ManufactureCategory persisted = repo.save(new ManufactureCategory(req));
			return new ManufactureCatContract(persisted);
		}
		else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Unable to add new Manufacture Category");
		}
	}

	@Override
	public ManufactureCatContract edit(Integer identity, ManufactureCatRequest req) throws ResponseStatusException {
		boolean isExist = repo.existsById(identity);
		if(isExist) {
			ManufactureCategory persisted = repo.save(new ManufactureCategory(req));
			return new ManufactureCatContract(persisted);
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Unable to edit this Manufacture Category");
		}
	}

	@Override
	public void remove(Integer identity) {
		repo.deleteById(identity);
	}

}

package org.narawit.comledger.coreapi.service.option;

import java.util.List;
import java.util.Optional;

import org.narawit.comledger.coreapi.contract.option.CdromTypeContract;
import org.narawit.comledger.coreapi.contract.option.CdromTypeRequest;
import org.narawit.comledger.coreapi.domain.option.CdromType;
import org.narawit.comledger.coreapi.repo.option.CdromTypeRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.narawit.comledger.coreapi.utilities.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class CdromTypeServiceimpl implements CdromTypeService {
	
	private CdromTypeRepo repo;
	
	public CdromTypeServiceimpl(CdromTypeRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public Iterable<CdromTypeContract> findAll() {
		Iterable<CdromType> rs = repo.findAll();
		List<CdromTypeContract> contracts = ServiceCommon.AddEntityToContractCollection(rs, e -> {
			return new CdromTypeContract(e); 
		});
		return contracts;
	}

	@Override
	public CdromTypeContract findById(Integer identity) {
		Optional<CdromType> rs = repo.findById(identity);
		if(rs.isPresent()) {
			return new CdromTypeContract(rs.get());
		}
		return null;
	}

	@Override
	@Transactional
	public CdromTypeContract add(CdromTypeRequest req) throws ResponseStatusException {
		if(StringUtils.isStringNotEmpty(req.type())) {
			if(!repo.existsByType(req.type())){
				CdromType persisted = repo.save(new CdromType(req));
				return new CdromTypeContract(persisted);
			}
			else {
				throw new ResponseStatusException(HttpStatus.CONFLICT, "Cdrom type with this type already exist");
			}
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cdrom Type is empty or invalid");
	}

	@Override
	@Transactional
	public CdromTypeContract edit(Integer identity, CdromTypeRequest req) throws ResponseStatusException {
		if(repo.existsById(identity)) {
			CdromType persisted = repo.save(new CdromType(req));
			return new CdromTypeContract(persisted);
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cdrom type is not exist");
		}
	}

	@Override
	@Transactional
	public void remove(Integer identity){
		repo.deleteById(identity);
	}
}

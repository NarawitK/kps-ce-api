package org.narawit.comledger.coreapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.narawit.comledger.coreapi.contract.PersonInitialContract;
import org.narawit.comledger.coreapi.contract.PersonInitialRequest;
import org.narawit.comledger.coreapi.domain.PersonInitial;
import org.narawit.comledger.coreapi.repo.PersonInitialRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.narawit.comledger.coreapi.utilities.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class PersonInitialServiceImpl implements PersonInitialService {
	private PersonInitialRepo repo;
	
	public PersonInitialServiceImpl(PersonInitialRepo repo) {
		this.repo = repo;
	}

	@Override
	public Iterable<PersonInitialContract> findAll() {
		Iterable<PersonInitial> rs = repo.findAll();
		List<PersonInitialContract> contracts = new ArrayList<>();
		if(ServiceCommon.IsIterableHasElements(rs)) {
			for(PersonInitial initial: rs) {
				contracts.add(new PersonInitialContract(initial));
			}
		}
		return contracts;
	}

	@Override
	public PersonInitialContract findById(Long identity) {
		Optional<PersonInitial> rs = repo.findById(identity);
		if(rs.isPresent())
			return new PersonInitialContract(rs.get());
		return null;
	}

	@Override
	@Transactional
	public PersonInitialContract add(PersonInitialRequest req) throws ResponseStatusException {
		if(StringUtils.isStringNotEmpty(req.initial())) {
			if(!repo.existsByInitial(req.initial())) {
				PersonInitial persisted = repo.save(new PersonInitial(req));
				return new PersonInitialContract(persisted);
			}
			else {
				throw new ResponseStatusException(HttpStatus.CONFLICT, "This initial is already exist");
			}
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Initial is empty");
	}

	@Override
	@Transactional
	public PersonInitialContract edit(Long identity, PersonInitialRequest req) throws ResponseStatusException {
		if(repo.existsById(identity)) {
			PersonInitial persisted = repo.save(new PersonInitial(req));
			return new PersonInitialContract(persisted);
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Initial is not exist");
	}

	@Override
	@Transactional
	public void remove(Long identity) {
		repo.deleteById(identity);
	}
}

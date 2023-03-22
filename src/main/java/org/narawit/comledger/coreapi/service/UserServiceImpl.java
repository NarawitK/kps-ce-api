package org.narawit.comledger.coreapi.service;

import java.util.List;
import java.util.Optional;

import org.narawit.comledger.coreapi.contract.UserContract;
import org.narawit.comledger.coreapi.contract.UserRequest;
import org.narawit.comledger.coreapi.domain.User;
import org.narawit.comledger.coreapi.repo.UserRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
	
	private final UserRepo repo;
	
	public UserServiceImpl(UserRepo repo) {
		this.repo = repo;
	}

	@Override
	public Iterable<UserContract> findAll() {
		Iterable<User> rs = repo.findAll();
		List<UserContract> contracts = ServiceCommon.AddEntityToContractCollection(rs, e -> {
			return new UserContract(e);
		});
		return contracts;
	}

	@Override
	public UserContract findById(Long identity) {
		Optional<User> rs = repo.findById(identity);
		if(rs.isPresent())
			return new UserContract(rs.get());
		return null;
	}

	@Override
	@Transactional
	public UserContract add(UserRequest req) throws ResponseStatusException {
		if(!repo.existsByEmail(req.email())) {
			return persistToDb(null, req);
		}
		else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this email already exist");
		}
	}

	@Override
	@Transactional
	public UserContract edit(Long identity, UserRequest req) throws ResponseStatusException {
		if(repo.existsById(identity)) {
			User persisted = repo.save(new User(identity, req));
			return new UserContract(persisted);
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not exist");
	}

	@Override
	@Transactional
	public void remove(Long identity) {
		repo.deleteById(identity);
	}
	
	private UserContract persistToDb(Long id, UserRequest req) {
		UserContract contract = null;
		if(id != null) {
			contract = new UserContract(repo.save(new User(id, req)));
		}
		else {
			contract = new UserContract(repo.save(new User(req)));
		}
		return contract;
	}
}

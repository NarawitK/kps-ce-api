package org.narawit.comledger.coreapi.service;

import java.util.List;
import java.util.Optional;

import org.narawit.comledger.coreapi.contract.UserContract;
import org.narawit.comledger.coreapi.contract.UserRequest;
import org.narawit.comledger.coreapi.domain.User;
import org.narawit.comledger.coreapi.repo.UserRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.narawit.comledger.coreapi.utilities.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
	
	private final UserRepo repo;
	private final PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepo repo, PasswordEncoder passwordEncoder) {
		this.repo = repo;
		this.passwordEncoder = passwordEncoder;
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
	public User findByUsername(String username) {
		Optional<User> user = repo.findByUsername(username);
		return user.isPresent() ? user.get() : null;
	}
	
	@Override
	@Transactional
	public UserContract add(UserRequest req) throws ResponseStatusException {
		if(!repo.existsByEmail(req.username())) {
			User user = new User(req);
			user.setPassword(passwordEncoder.encode(req.password()));
			return new UserContract(repo.save(user));
		}
		else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this email already exist");
		}
	}
	
	@Override
	@Transactional
	public User addThenReturnEntity(UserRequest req) throws ResponseStatusException {
		if(!repo.existsByEmail(req.username())) {
			User user = new User(req);
			user.setPassword(passwordEncoder.encode(req.password()));
			return repo.save(user);
		}
		else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this email already exist");
		}
	}

	@Override
	@Transactional
	public UserContract edit(Long identity, UserRequest req) throws ResponseStatusException {
		Optional<User> result = repo.findById(identity);
		if(result.isPresent()) {
			User user = result.get();
			User persisted = new User(identity, req);
			// User provided new Password in Front-end
			if(StringUtils.isStringNotEmpty(req.password())) {
				persisted.setPassword(passwordEncoder.encode(req.password()));
			}
			// User didn't change their password in Front-end
			else {
				persisted.setPassword(user.getPassword());
			}
			return new UserContract(repo.save(persisted));
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist");
		}
	}

	@Override
	@Transactional
	public void remove(Long identity) {
		repo.deleteById(identity);
	}
}

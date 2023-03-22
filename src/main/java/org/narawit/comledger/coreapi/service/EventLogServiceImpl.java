package org.narawit.comledger.coreapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.narawit.comledger.coreapi.contract.EventLogContract;
import org.narawit.comledger.coreapi.contract.EventLogRequest;
import org.narawit.comledger.coreapi.domain.EventLog;
import org.narawit.comledger.coreapi.repo.EventLogRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.narawit.comledger.coreapi.utilities.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class EventLogServiceImpl implements EventLogService {

	private EventLogRepo repo;
	
	public EventLogServiceImpl(EventLogRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public Iterable<EventLogContract> findAll() {
		Iterable<EventLog> rs = repo.findAll();
		List<EventLogContract> contracts = new ArrayList<>();
		if(ServiceCommon.IsIterableHasElements(rs)) {
			for(EventLog log : rs) {
				contracts.add(new EventLogContract(log));
			}
		}
		return contracts;
	}

	@Override
	public EventLogContract findById(Long identity) {
		Optional<EventLog> rs = repo.findById(identity);
		if(rs.isPresent())
			return new EventLogContract(rs.get());
		return null;
	}

	@Override
	@Transactional
	public EventLogContract add(EventLogRequest req) throws ResponseStatusException {
		if(StringUtils.isStringNotEmpty(req.header())) {
			return this.persistToDb(null, req);
		}
		throw new ResponseStatusException(HttpStatus.CONFLICT, "This event log already exist");
	}

	@Override
	@Transactional
	public EventLogContract edit(Long identity, EventLogRequest req) throws ResponseStatusException {
		if(repo.existsById(identity)) {
			return this.persistToDb(identity, req);
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This equipment location is not exist");			
		}
	}

	@Override
	@Transactional
	public void remove(Long identity) {
		repo.deleteById(identity);
	}
	
	private EventLogContract persistToDb(Long identity, EventLogRequest req) {
		EventLogContract contract = null;
		if(identity != null) {
			contract = new EventLogContract(repo.save(new EventLog(identity, req)));
		}
		else {
			contract = new EventLogContract(repo.save(new EventLog(req)));
		}
		return contract;
	}
}

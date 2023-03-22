package org.narawit.comledger.coreapi.service.common;

import org.springframework.web.server.ResponseStatusException;

public interface BaseService<CONTRACT, REQUEST, ID> {
	public Iterable<CONTRACT> findAll();
	public CONTRACT findById(ID identity);
	public CONTRACT add(REQUEST req) throws ResponseStatusException;
	public CONTRACT edit(ID identity, REQUEST req) throws ResponseStatusException;
	public void remove(ID identity);
}
package org.narawit.comledger.coreapi.contract;

import java.time.ZonedDateTime;

import org.narawit.comledger.coreapi.domain.EventLog;

public record EventLogContract(Long id, String operation, String header, String detail, ZonedDateTime logdate) {
	public EventLogContract(EventLog entity) {
		this(
				entity.getId(),
				entity.getOperation(), 
				entity.getHeader(), 
				entity.getDetail(), 
				entity.getLogDate()
			);
	}
}

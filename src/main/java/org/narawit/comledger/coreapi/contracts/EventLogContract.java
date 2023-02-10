package org.narawit.comledger.coreapi.contracts;

import java.time.ZonedDateTime;

import org.narawit.comledger.coreapi.domain.EventLog;

public record EventLogContract(String operation, String header, String detail, ZonedDateTime logdate) {
	public EventLogContract(EventLog entity) {
		this(
				entity.getOperation(), 
				entity.getHeader(), 
				entity.getDetail(), 
				entity.getLogDate()
			);
	}
}

package org.narawit.comledger.coreapi.contract;

import java.time.ZonedDateTime;

import org.narawit.comledger.coreapi.domain.MaintenanceHistory;

public record MaintenanceContract(
		Long equipmentId,
		String detail,
		ZonedDateTime createAt,
		boolean done,
		ZonedDateTime doneDate,
		Long userId
		) {
	public MaintenanceContract(MaintenanceHistory entity) {
		this(
				entity.getEquipment().getId(), 
				entity.getDetail(), 
				entity.getCreatedAt(), 
				entity.isDone(), 
				entity.getDoneDate(), 
				entity.getCreatedByUser().getId());
	}

}

package org.narawit.comledger.coreapi.contracts;

import java.time.ZonedDateTime;

import org.narawit.comledger.coreapi.domain.Equipment;

public record EquipmentContract(
		String name,
		String description,
		String identifier,
		String serialNumber,
		boolean active,
		ZonedDateTime importDate,
		ZonedDateTime registerDate
		) {
	public EquipmentContract(Equipment entity) {
		this(
				entity.getName(), 
				entity.getDescription(), 
				entity.getInternalIdentifier(), 
				entity.getSerialNumber(),
				entity.getActive(),
				entity.getImportDate(),
				entity.getRegisterDate()
				);
	}
}
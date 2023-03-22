package org.narawit.comledger.coreapi.contract;

import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EquipmentRequest(
		@NotBlank
		String name,
		@NotBlank
		Long equipmentTypeId,
		@NotBlank
		String description,
		String identifier,
		String serialNumber,
		@NotBlank
		boolean active,
		@NotNull
		ZonedDateTime importDate,
		@NotNull
		ZonedDateTime registerDate
		) {

}

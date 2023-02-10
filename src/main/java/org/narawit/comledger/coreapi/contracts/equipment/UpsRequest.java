package org.narawit.comledger.coreapi.contracts.equipment;

import jakarta.validation.constraints.NotBlank;

public record UpsRequest(
		@NotBlank
		Long equipmentId,
		@NotBlank
		Integer manufactureId,
		@NotBlank
		String model,
		@NotBlank
		Double capacity,
		@NotBlank
		Integer capacityUnitId
		) {
}

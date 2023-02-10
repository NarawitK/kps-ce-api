package org.narawit.comledger.coreapi.contracts.equipment;

import jakarta.validation.constraints.NotBlank;

public record RamRequest(
		@NotBlank
		Integer manufactureId,
		@NotBlank
		String model,
		@NotBlank
		String memoryType,
		@NotBlank
		Short capacity,
		@NotBlank
		Integer capacityUnitId) {}

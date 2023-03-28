package org.narawit.comledger.coreapi.contracts.equipment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpsRequest(
		@NotNull(message = "Equipment must be specify.")
		Long equipmentId,
		@NotNull(message = "Manufacture must be specify.")
		Integer manufactureId,
		@NotBlank(message = "Model must be specify.")
		String model,
		@NotNull(message = "Capacity must be specify.")
		Double capacity,
		@NotNull(message = "Capacity Unit must be specify.")
		Integer capacityUnitId) {}
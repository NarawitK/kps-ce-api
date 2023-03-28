package org.narawit.comledger.coreapi.contracts.equipment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VgaRequest(
		@NotNull(message = "Equipment must be specify")
		Long equipmentId,
		@NotNull(message = "Manufacture must be specify.")
		Integer manufactureId, 
		@NotBlank(message = "Model cannot be empty.")
		String model, 
		@NotNull(message = "Memory Size must be specify.")
		Double memory, 
		@NotNull(message = "Memory Unit must be specify.")
		Integer memoryUnitId) {}

package org.narawit.comledger.coreapi.contracts.equipment;

import jakarta.validation.constraints.NotBlank;

public record VgaRequest(
		@NotBlank 
		Long equipmentId,
		@NotBlank
		Integer manufactureId, 
		@NotBlank
		String model, 
		@NotBlank
		Double memory, 
		@NotBlank
		Integer memoryUnit) {}

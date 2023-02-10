package org.narawit.comledger.coreapi.contracts.equipment;

import jakarta.validation.constraints.NotBlank;

public record CpuRequest(
		@NotBlank
		Integer manufactureId,
		@NotBlank
		String model,
		@NotBlank
		Short threadCount,
		@NotBlank
		Double clockSpeed,
		@NotBlank
		Integer clockSpeedUnitId) {
}

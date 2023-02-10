package org.narawit.comledger.coreapi.contracts.equipment;

import jakarta.validation.constraints.NotBlank;

public record NicRequest(
		@NotBlank
		Long equipmentId,
		@NotBlank
		Integer manufactureId,
		@NotBlank
		Integer connectionTypeId,
		Integer connectorTypeId,
		@NotBlank
		String model,
		@NotBlank
		Double speed,
		@NotBlank
		Integer speedUnitId) {
}

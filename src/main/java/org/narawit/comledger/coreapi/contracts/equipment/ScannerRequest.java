package org.narawit.comledger.coreapi.contracts.equipment;

import jakarta.validation.constraints.NotBlank;

public record ScannerRequest(
		@NotBlank
		Long equipmentId,
		@NotBlank
		Integer manufactureId,
		@NotBlank
		String model) {

}

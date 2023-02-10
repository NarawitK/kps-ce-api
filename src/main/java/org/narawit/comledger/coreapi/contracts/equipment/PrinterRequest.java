package org.narawit.comledger.coreapi.contracts.equipment;

import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotBlank;

public record PrinterRequest(
		@NotBlank
		Long equipmentId,
		@NotBlank
		Integer manufactureId,
		@NotBlank
		Integer printerTypeId,
		@NotBlank
		String model,
		@NotBlank
		String inkModel,
		ZonedDateTime lastInkReloadDate) {
}
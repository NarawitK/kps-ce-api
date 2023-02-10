package org.narawit.comledger.coreapi.contracts;

import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MaintenanceRequest(
		@NotBlank
		Long equipmentId,
		@NotBlank
		String detail,
		@NotBlank
		ZonedDateTime createAt,
		@NotNull
		boolean done,
		ZonedDateTime doneDate,
		@NotBlank
		Long userId
		) {}

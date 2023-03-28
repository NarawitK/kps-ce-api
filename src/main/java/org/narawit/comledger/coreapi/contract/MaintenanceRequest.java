package org.narawit.comledger.coreapi.contract;

import static org.narawit.comledger.coreapi.constant.MessageConstants.NOT_BLANK_VALIDATION_MSG;
import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MaintenanceRequest(
		@NotNull(message = "Equipment" + NOT_BLANK_VALIDATION_MSG)
		Long equipmentId,
		@NotBlank(message = "Maintenance Detail" + NOT_BLANK_VALIDATION_MSG)
		String detail,
		@NotBlank(message = "Maintenance Creation Date" + NOT_BLANK_VALIDATION_MSG)
		ZonedDateTime createAt,
		boolean done,
		@NotNull(message = "Task Done" + NOT_BLANK_VALIDATION_MSG)
		ZonedDateTime doneDate,
		@NotNull(message = "User" + NOT_BLANK_VALIDATION_MSG)
		Long userId
		) {}

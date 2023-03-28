package org.narawit.comledger.coreapi.contracts.equipment;

import static org.narawit.comledger.coreapi.constant.MessageConstants.NOT_BLANK_VALIDATION_MSG;
import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PrinterRequest(
		@NotNull(message = "Equipment" + NOT_BLANK_VALIDATION_MSG)
		Long equipmentId,
		@NotNull(message = "Manufacture" + NOT_BLANK_VALIDATION_MSG)
		Integer manufactureId,
		@NotNull(message = "Printer Type" + NOT_BLANK_VALIDATION_MSG)
		Integer printerTypeId,
		@NotBlank(message = "Printer Model" + NOT_BLANK_VALIDATION_MSG)
		String model,
		@NotBlank(message = "Ink Model" + NOT_BLANK_VALIDATION_MSG)
		String inkModel,
		ZonedDateTime lastInkReloadDate) {}
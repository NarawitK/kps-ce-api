package org.narawit.comledger.coreapi.contracts.equipment;

import static org.narawit.comledger.coreapi.constant.MessageConstants.NOT_BLANK_VALIDATION_MSG;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RamRequest(
		@NotNull(message = "RAM Manufacture" + NOT_BLANK_VALIDATION_MSG)
		Integer manufactureId,
		@NotBlank(message = "Model" + NOT_BLANK_VALIDATION_MSG)
		String model,
		@NotBlank(message = "Memory Type" + NOT_BLANK_VALIDATION_MSG)
		String memoryType,
		@NotNull(message = "RAM Memory Capacity" + NOT_BLANK_VALIDATION_MSG)
		Short capacity,
		@NotNull(message = "Capacity Unit" + NOT_BLANK_VALIDATION_MSG)
		Integer capacityUnitId) {}

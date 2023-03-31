package org.narawit.comledger.coreapi.contracts.equipment;

import static org.narawit.comledger.coreapi.constant.MessageConstants.NOT_BLANK_VALIDATION_MSG;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CpuRequest(
		@NotNull(message = "CPU Manufacture" + NOT_BLANK_VALIDATION_MSG)
		Integer manufactureId,
		@NotBlank(message = "CPU Model" + NOT_BLANK_VALIDATION_MSG)
		String model,
		Short coreCount,
		Short threadCount,
		Double clockSpeed,
		Integer clockSpeedUnitId) {
}
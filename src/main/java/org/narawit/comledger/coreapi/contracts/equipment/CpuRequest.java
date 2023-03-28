package org.narawit.comledger.coreapi.contracts.equipment;

import static org.narawit.comledger.coreapi.constant.MessageConstants.NOT_BLANK_VALIDATION_MSG;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CpuRequest(
		@NotNull(message = "CPU Manufacture" + NOT_BLANK_VALIDATION_MSG)
		Integer manufactureId,
		@NotBlank(message = "CPU Model" + NOT_BLANK_VALIDATION_MSG)
		String model,
		@NotNull(message = "CPU Thread" + NOT_BLANK_VALIDATION_MSG)
		Short threadCount,
		@NotNull(message = "CPU Clock Speed" + NOT_BLANK_VALIDATION_MSG)
		Double clockSpeed,
		@NotNull(message = "Clock Speed Unit" + NOT_BLANK_VALIDATION_MSG)
		Integer clockSpeedUnitId) {
}
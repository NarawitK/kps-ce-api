package org.narawit.comledger.coreapi.contracts.equipment;

import static org.narawit.comledger.coreapi.constant.MessageConstants.NOT_BLANK_VALIDATION_MSG;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LaptopRequest(
		@NotNull(message = "Equipment" + NOT_BLANK_VALIDATION_MSG)
		Long equipmentId,
		@NotBlank(message = "Manufacture" + NOT_BLANK_VALIDATION_MSG)
		Integer manufactureId,
		@NotBlank(message = "Laptop Model" + NOT_BLANK_VALIDATION_MSG)
		String model,
		@NotNull(message = "Laptop CPU" + NOT_BLANK_VALIDATION_MSG)
		Long cpuId,
		@NotNull(message = "RAM" + NOT_BLANK_VALIDATION_MSG)
		Long ramId,
		String pointerDevice,
		String keyboard,
		Integer cdRomTypeId) {
}

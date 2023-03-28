package org.narawit.comledger.coreapi.contracts.equipment;

import static org.narawit.comledger.coreapi.constant.MessageConstants.NOT_BLANK_VALIDATION_MSG;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ScannerRequest(
		@NotNull(message = "Equipment" + NOT_BLANK_VALIDATION_MSG)
		Long equipmentId,
		@NotNull(message = "Manufacture" + NOT_BLANK_VALIDATION_MSG)
		Integer manufactureId,
		@NotBlank(message = "Scanner Model" + NOT_BLANK_VALIDATION_MSG)
		String model) {

}

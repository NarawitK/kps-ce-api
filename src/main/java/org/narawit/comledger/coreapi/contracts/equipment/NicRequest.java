package org.narawit.comledger.coreapi.contracts.equipment;

import static org.narawit.comledger.coreapi.constant.MessageConstants.NOT_BLANK_VALIDATION_MSG;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NicRequest(
		@NotNull(message = "Equipment" + NOT_BLANK_VALIDATION_MSG)
		Long equipmentId,
		@NotNull(message = "Manufacture" + NOT_BLANK_VALIDATION_MSG)
		Integer manufactureId,
		@NotNull(message = "Connection Type" + NOT_BLANK_VALIDATION_MSG)
		Integer connectionTypeId,
		@NotNull(message = "Connector Type" + NOT_BLANK_VALIDATION_MSG)
		Integer connectorTypeId,
		@NotBlank(message = "Network Interface Card Model" + NOT_BLANK_VALIDATION_MSG)
		String model,
		@NotNull(message = "Network Interface Card Speed" + NOT_BLANK_VALIDATION_MSG)
		Double speed,
		@NotNull(message = "Network Speed Unit" + NOT_BLANK_VALIDATION_MSG)
		Integer speedUnitId) {
}

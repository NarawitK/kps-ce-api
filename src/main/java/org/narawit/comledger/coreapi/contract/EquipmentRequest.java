package org.narawit.comledger.coreapi.contract;

import static org.narawit.comledger.coreapi.constant.MessageConstants.NOT_BLANK_VALIDATION_MSG;
import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EquipmentRequest(
		@NotBlank(message = "Equipment Name" + NOT_BLANK_VALIDATION_MSG)
		String name,
		@NotBlank(message = "Equipment Type" + NOT_BLANK_VALIDATION_MSG)
		Long equipmentTypeId,
		@NotBlank(message = "Description" + NOT_BLANK_VALIDATION_MSG)
		String description,
		String identifier,
		String serialNumber,
		@NotNull(message = "Active State" + NOT_BLANK_VALIDATION_MSG)
		boolean active,
		@NotNull(message = "Import Date" + NOT_BLANK_VALIDATION_MSG)
		ZonedDateTime importDate,
		ZonedDateTime registerDate
		) {

}

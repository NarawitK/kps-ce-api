package org.narawit.comledger.coreapi.contract;

import static org.narawit.comledger.coreapi.constant.MessageConstants.NOT_BLANK_VALIDATION_MSG;
import java.time.ZonedDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EquipmentLocationRequest(
		@NotBlank(message = "Associate Department" + NOT_BLANK_VALIDATION_MSG)
		Integer deptId,
		Integer subDeptId,
		@NotNull(message = "Associate Equipment" + NOT_BLANK_VALIDATION_MSG)
		Long equipmentId,
		@NotBlank(message = "Equipment Assigned Date" + NOT_BLANK_VALIDATION_MSG)
		ZonedDateTime assignedDate,
		@NotNull(message = "Active State" + NOT_BLANK_VALIDATION_MSG)
		boolean active, 
		ZonedDateTime lastDeactiveDate) {}
package org.narawit.comledger.coreapi.contract;

import static org.narawit.comledger.coreapi.constant.MessageConstants.NOT_BLANK_VALIDATION_MSG;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DepartmentRequest(
		@NotBlank(message = "Department Name" + NOT_BLANK_VALIDATION_MSG)
		String name,
		@NotNull(message = "Active State" + NOT_BLANK_VALIDATION_MSG)
		boolean active) {}
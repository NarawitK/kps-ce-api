package org.narawit.comledger.coreapi.contract;

import static org.narawit.comledger.coreapi.constant.MessageConstants.NOT_BLANK_VALIDATION_MSG; 
import jakarta.validation.constraints.NotNull;

public record SubDepartmentRequest(
		@NotNull(message = "Department"+ NOT_BLANK_VALIDATION_MSG)
		Integer deptId,
		@NotNull(message = "SubDepartment Name"+ NOT_BLANK_VALIDATION_MSG)
		String name,
		boolean active) {
}

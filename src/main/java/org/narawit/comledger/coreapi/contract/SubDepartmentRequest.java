package org.narawit.comledger.coreapi.contract;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SubDepartmentRequest(
		Integer deptId,
		@NotBlank
		String name,
		@NotNull
		boolean active) {
}

package org.narawit.comledger.coreapi.contracts;

import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotBlank;

public record EquipmentLocationRequest(
		@NotBlank
		Integer deptId,
		Integer subDeptId,
		@NotBlank
		Long equipmentId,
		@NotBlank
		ZonedDateTime assignedDate,
		@NotBlank
		boolean active, 
		ZonedDateTime lastDeactiveDate) {}
package org.narawit.comledger.coreapi.contract.option;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UnitRequest(
		@NotNull(message = "Unit Type must be specify.")
		Integer unitTypeId, 
		@NotBlank(message = "Unit cannot be blank.")
		String unit) {}

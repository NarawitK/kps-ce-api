package org.narawit.comledger.coreapi.contract.option;

import jakarta.validation.constraints.NotBlank;

public record UnitRequest(
		@NotBlank(message = "Unit Type must be specify.")
		Integer unitTypeId, 
		@NotBlank(message = "Unit cannot be blank.")
		String unit) {}

package org.narawit.comledger.coreapi.contract.option;

import static org.narawit.comledger.coreapi.constant.MessageConstants.NOT_BLANK_VALIDATION_MSG;
import jakarta.validation.constraints.NotBlank;

public record ManufactureCatRequest(
		@NotBlank(message = "Manufacture Category Name" + NOT_BLANK_VALIDATION_MSG)
		String category) {}

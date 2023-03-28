package org.narawit.comledger.coreapi.contract;

import static org.narawit.comledger.coreapi.constant.MessageConstants.NOT_BLANK_VALIDATION_MSG;
import jakarta.validation.constraints.NotBlank;

public record PersonInitialRequest(
		@NotBlank(message = "Initial" + NOT_BLANK_VALIDATION_MSG)
		String initial) {}
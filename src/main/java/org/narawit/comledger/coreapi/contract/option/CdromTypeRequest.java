package org.narawit.comledger.coreapi.contract.option;

import static org.narawit.comledger.coreapi.constant.MessageConstants.NOT_BLANK_VALIDATION_MSG;
import jakarta.validation.constraints.NotBlank;

public record CdromTypeRequest(
		@NotBlank(message = "CDROM Type" + NOT_BLANK_VALIDATION_MSG)
		String type) {}

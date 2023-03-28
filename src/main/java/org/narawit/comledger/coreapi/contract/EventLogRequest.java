package org.narawit.comledger.coreapi.contract;

import static org.narawit.comledger.coreapi.constant.MessageConstants.NOT_BLANK_VALIDATION_MSG;
import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotBlank;

public record EventLogRequest(
		@NotBlank(message = "Operation" + NOT_BLANK_VALIDATION_MSG)
		String operation,
		@NotBlank(message = "Log Header" + NOT_BLANK_VALIDATION_MSG)
		String header,
		String detail,
		ZonedDateTime logdate
		) {}
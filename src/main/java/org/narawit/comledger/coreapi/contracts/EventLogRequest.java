package org.narawit.comledger.coreapi.contracts;

import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotBlank;

public record EventLogRequest(
		@NotBlank
		String operation,
		@NotBlank
		String header,
		String detail,
		ZonedDateTime logdate
		) {

}

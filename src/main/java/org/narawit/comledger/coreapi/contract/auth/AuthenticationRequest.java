package org.narawit.comledger.coreapi.contract.auth;

import static org.narawit.comledger.coreapi.constant.MessageConstants.NOT_BLANK_VALIDATION_MSG;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
		@NotBlank(message = "Username" + NOT_BLANK_VALIDATION_MSG)
		String username, 
		@NotBlank(message = "Password" + NOT_BLANK_VALIDATION_MSG)
		String password) {}
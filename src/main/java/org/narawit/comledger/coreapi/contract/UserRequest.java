package org.narawit.comledger.coreapi.contract;

import static org.narawit.comledger.coreapi.constant.MessageConstants.NOT_BLANK_VALIDATION_MSG;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
		@NotNull(message = "Intitial" + NOT_BLANK_VALIDATION_MSG)
		Long initialId,
		@NotBlank(message = "User Role" + NOT_BLANK_VALIDATION_MSG)
		String role,
		@NotBlank(message = "First Name" + NOT_BLANK_VALIDATION_MSG)
		String firstname,
		@NotBlank(message = "Last Name" + NOT_BLANK_VALIDATION_MSG)
		String lastname, 
		@NotBlank(message = "Email" + NOT_BLANK_VALIDATION_MSG)
		String email, 
		@NotBlank(message = "Username" + NOT_BLANK_VALIDATION_MSG)
		String username,
		String password,
		@NotNull(message = "Activation state" + NOT_BLANK_VALIDATION_MSG)
		Boolean active) {}
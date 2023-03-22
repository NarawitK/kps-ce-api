package org.narawit.comledger.coreapi.contract;

import jakarta.validation.constraints.NotBlank;

public record UserRequest(
		@NotBlank
		Long initialId,
		@NotBlank
		String firstname,
		@NotBlank
		String lastname, 
		@NotBlank
		String email, 
		@NotBlank
		String username,
		@NotBlank
		String password,
		@NotBlank
		Boolean active) {
}

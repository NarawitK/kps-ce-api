package org.narawit.comledger.coreapi.contracts;

import jakarta.validation.constraints.NotBlank;

public record UserRequest(
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
		Boolean active) {
}

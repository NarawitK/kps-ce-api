package org.narawit.comledger.coreapi.contracts;

import org.narawit.comledger.coreapi.domain.User;

public record UserContract(String firstname, String lastname, String email, String username, String password, Boolean active) {
	public UserContract(User user) {
		this(user.getFirstname(), user.getLastname(), user.getEmail(), user.getUsername(), user.getPassword(), user.getActive());
	}
}

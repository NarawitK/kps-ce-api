package org.narawit.comledger.coreapi.contract;

import java.time.ZonedDateTime;

import org.narawit.comledger.coreapi.domain.Role;
import org.narawit.comledger.coreapi.domain.User;

public record UserContract(Long id, Role role, PersonInitialContract initial, String firstname, String lastname, String email, String username, String password, ZonedDateTime lastLogin, Boolean active) {
	public UserContract(User user) {
		this(user.getId(), user.getRole(), new PersonInitialContract(user.getInitial()), user.getFirstname(), user.getLastname(), user.getEmail(), user.getUsername(), user.getPassword(), user.getLastLogin(), user.getActive());
	}
}

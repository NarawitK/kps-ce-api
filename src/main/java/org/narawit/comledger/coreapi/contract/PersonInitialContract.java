package org.narawit.comledger.coreapi.contract;

import org.narawit.comledger.coreapi.domain.PersonInitial;

public record PersonInitialContract(String initial) {
	public PersonInitialContract(PersonInitial entity) {
		this(entity.getInitial());
	}
}

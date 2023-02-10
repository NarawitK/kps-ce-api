package org.narawit.comledger.coreapi.contracts.option;

import org.narawit.comledger.coreapi.domain.option.Unit;

public record UnitContract(Integer id, String type) {
	public UnitContract(Unit unit) {
		this(unit.getId(), unit.getUnit());
	}
}
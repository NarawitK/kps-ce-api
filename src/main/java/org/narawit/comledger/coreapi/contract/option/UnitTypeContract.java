package org.narawit.comledger.coreapi.contract.option;

import org.narawit.comledger.coreapi.domain.option.UnitType;

public record UnitTypeContract(Integer id, String type) {
	public UnitTypeContract(UnitType unitType) {
		this(unitType.getId(), unitType.getType());
	}
}

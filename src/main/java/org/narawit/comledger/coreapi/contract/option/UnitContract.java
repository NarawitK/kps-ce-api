package org.narawit.comledger.coreapi.contract.option;

import org.narawit.comledger.coreapi.domain.option.Unit;

public record UnitContract(Integer id, UnitTypeContract unitType, String unit) {
	public UnitContract(Unit unit) {
		this(unit.getId(), new UnitTypeContract(unit.getUnitType()), unit.getUnit());
	}
}
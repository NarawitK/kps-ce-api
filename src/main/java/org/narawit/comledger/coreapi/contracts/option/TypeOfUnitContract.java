package org.narawit.comledger.coreapi.contracts.option;

import org.narawit.comledger.coreapi.domain.option.TypeOfUnit;

public record TypeOfUnitContract(Integer id, String type) {
	public TypeOfUnitContract(TypeOfUnit typeOfUnit) {
		this(typeOfUnit.getId(), typeOfUnit.getType());
	}
}

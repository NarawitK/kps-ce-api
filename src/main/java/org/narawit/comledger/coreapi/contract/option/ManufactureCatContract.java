package org.narawit.comledger.coreapi.contract.option;

import org.narawit.comledger.coreapi.domain.option.ManufactureCategory;

public record ManufactureCatContract(Integer id, String category) {
	public ManufactureCatContract(ManufactureCategory category) {
		this(category.getId(), category.getCategory());
	}
}

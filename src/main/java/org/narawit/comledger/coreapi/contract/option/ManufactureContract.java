package org.narawit.comledger.coreapi.contract.option;

import org.narawit.comledger.coreapi.domain.option.Manufacture;

public record ManufactureContract(Integer id, String name) {
	public ManufactureContract(Manufacture manufacture) {
		this(manufacture.getId(), manufacture.getName());
	}
}

package org.narawit.comledger.coreapi.contract.option;

import org.narawit.comledger.coreapi.domain.option.CdromType;

public record CdromTypeContract(Integer id, String type) {
	public CdromTypeContract(CdromType entity) { 
		this(entity.getId(), entity.getType()); 
	}
}

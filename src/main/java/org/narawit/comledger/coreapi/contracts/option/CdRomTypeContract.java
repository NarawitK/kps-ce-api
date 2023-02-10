package org.narawit.comledger.coreapi.contracts.option;

import org.narawit.comledger.coreapi.domain.option.CdromType;

public record CdRomTypeContract(String type) {
	public CdRomTypeContract(CdromType entity) { this(entity.getType()); }
}

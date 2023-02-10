package org.narawit.comledger.coreapi.contracts.option;

import org.narawit.comledger.coreapi.domain.option.NetworkConnectionType;

public record NetworkConnectionTypeContract(Integer id, String connectionType) {
	public NetworkConnectionTypeContract(NetworkConnectionType entity) {
		this(entity.getId(), entity.getConnectionType());
	}
}

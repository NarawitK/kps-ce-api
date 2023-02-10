package org.narawit.comledger.coreapi.contracts.option;

import org.narawit.comledger.coreapi.domain.option.NetworkConnectorType;

public record NetworkConnectorTypeContract(Integer id, String connector) {
	public NetworkConnectorTypeContract(NetworkConnectorType entity) {
		this(entity.getId(), entity.getConnector());
	}
}

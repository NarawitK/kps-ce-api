package org.narawit.comledger.coreapi.repo.option;

import org.narawit.comledger.coreapi.domain.option.NetworkConnectorType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NetworkConnectorTypeRepo extends JpaRepository<NetworkConnectorType, Integer> {
	public boolean existsByConnector(String connector);
}

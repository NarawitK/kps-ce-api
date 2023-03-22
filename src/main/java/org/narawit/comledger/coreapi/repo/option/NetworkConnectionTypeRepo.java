package org.narawit.comledger.coreapi.repo.option;

import org.narawit.comledger.coreapi.domain.option.NetworkConnectionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NetworkConnectionTypeRepo extends JpaRepository<NetworkConnectionType, Integer> {
	public boolean existsByConnectionType(String connectionType);
}

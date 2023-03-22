package org.narawit.comledger.coreapi.service.option;

import java.util.List;
import java.util.Optional;
import org.narawit.comledger.coreapi.contract.option.NetworkConnectorTypeContract;
import org.narawit.comledger.coreapi.contract.option.NetworkConnectorTypeRequest;
import org.narawit.comledger.coreapi.domain.option.NetworkConnectorType;
import org.narawit.comledger.coreapi.repo.option.NetworkConnectorTypeRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.narawit.comledger.coreapi.utilities.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class NetworkConnectorTypeServiceImpl implements NetworkConnectorTypeService {
	
	private final NetworkConnectorTypeRepo repo;
	
	public NetworkConnectorTypeServiceImpl(NetworkConnectorTypeRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public Iterable<NetworkConnectorTypeContract> findAll() {
		Iterable<NetworkConnectorType> result = repo.findAll();
		List<NetworkConnectorTypeContract> contracts = ServiceCommon.AddEntityToContractCollection(result, e -> {
			return new NetworkConnectorTypeContract(e);
		});
		return contracts;
	}

	@Override
	public NetworkConnectorTypeContract findById(Integer identity) {
		Optional<NetworkConnectorType> result = repo.findById(identity);
		if(result.isPresent()) {
			return new NetworkConnectorTypeContract(result.get());
		}
		return null;
	}

	@Override
	@Transactional
	public NetworkConnectorTypeContract add(NetworkConnectorTypeRequest req) throws ResponseStatusException {
		String connector = req.connector();
		if(StringUtils.isStringNotEmpty(connector)) {
			if(!repo.existsByConnector(connector)) {
				NetworkConnectorType persisted = repo.save(new NetworkConnectorType(req));
				return new NetworkConnectorTypeContract(persisted);
			}
			else {
				throw new ResponseStatusException(HttpStatus.CONFLICT, "Cannot add new connector");
			}
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Connector Request is empty");
		}
	}

	@Override
	@Transactional
	public NetworkConnectorTypeContract edit(Integer identity, NetworkConnectorTypeRequest req) throws ResponseStatusException {
		if(repo.existsById(identity)) {
			NetworkConnectorType persisted = repo.save(new NetworkConnectorType(identity, req));
			return new NetworkConnectorTypeContract(persisted);
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Identity is required for editing Network Connector");
		}
	}

	@Override
	@Transactional
	public void remove(Integer identity) {
		repo.deleteById(identity);
	}
}

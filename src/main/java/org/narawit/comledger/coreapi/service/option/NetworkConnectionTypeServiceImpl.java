package org.narawit.comledger.coreapi.service.option;

import java.util.List;
import java.util.Optional;

import org.narawit.comledger.coreapi.contract.option.NetworkConnectionTypeContract;
import org.narawit.comledger.coreapi.contract.option.NetworkConnectionTypeRequest;
import org.narawit.comledger.coreapi.domain.option.NetworkConnectionType;
import org.narawit.comledger.coreapi.repo.option.NetworkConnectionTypeRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.narawit.comledger.coreapi.utilities.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class NetworkConnectionTypeServiceImpl implements NetworkConnectionTypeService{

	private final NetworkConnectionTypeRepo repo;
	
	public NetworkConnectionTypeServiceImpl(NetworkConnectionTypeRepo repo) {
		this.repo = repo;
	}
	@Override
	public Iterable<NetworkConnectionTypeContract> findAll() {
		Iterable<NetworkConnectionType> result = repo.findAll();
		List<NetworkConnectionTypeContract> contracts = ServiceCommon.AddEntityToContractCollection(result, (e) -> {
			return new NetworkConnectionTypeContract(e);
		});
		return contracts;
	}

	@Override
	public NetworkConnectionTypeContract findById(Integer identity) {
		Optional<NetworkConnectionType> result = repo.findById(identity);
		if(result.isPresent()) {
			return new NetworkConnectionTypeContract(result.get());
		}
		return null;
	}

	@Override
	public NetworkConnectionTypeContract add(NetworkConnectionTypeRequest req) throws ResponseStatusException {
		String connectionType = req.connectionType();
		if(StringUtils.isStringNotEmpty(connectionType)) {
			if(!repo.existsByConnectionType(connectionType)) {
				NetworkConnectionType persisted = repo.save(new NetworkConnectionType(req));
				return new NetworkConnectionTypeContract(persisted);
			}
			else {
				throw new ResponseStatusException(HttpStatus.CONFLICT, "This Connection Type already exist");
			}
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ConnectionType is empty");
	}

	@Override
	public NetworkConnectionTypeContract edit(Integer identity, NetworkConnectionTypeRequest req) throws ResponseStatusException {
		if(repo.existsById(identity)) {
			NetworkConnectionType persisted = repo.save(new NetworkConnectionType(req));
			return new NetworkConnectionTypeContract(persisted);
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This ConnectionType entry does not exist");
	}

	@Override
	public void remove(Integer identity) {
		repo.deleteById(identity);
	}
}

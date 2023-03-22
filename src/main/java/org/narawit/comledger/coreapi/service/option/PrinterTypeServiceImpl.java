package org.narawit.comledger.coreapi.service.option;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.narawit.comledger.coreapi.contract.option.PrinterTypeContract;
import org.narawit.comledger.coreapi.contract.option.PrinterTypeRequest;
import org.narawit.comledger.coreapi.domain.option.PrinterType;
import org.narawit.comledger.coreapi.repo.option.PrinterTypeRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.narawit.comledger.coreapi.utilities.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PrinterTypeServiceImpl implements PrinterTypeService {

	private final PrinterTypeRepo repo;
	
	public PrinterTypeServiceImpl(PrinterTypeRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public Iterable<PrinterTypeContract> findAll() {
		Iterable<PrinterType> result = repo.findAll();
		List<PrinterTypeContract> contracts = new ArrayList<>();
		if(ServiceCommon.IsIterableHasElements(result)) {
			for(PrinterType type : result) {
				contracts.add(new PrinterTypeContract(type));
			}
		}
		return contracts;
	}

	@Override
	public PrinterTypeContract findById(Integer identity) {
		Optional<PrinterType> result = repo.findById(identity);
		return result.isPresent() ? new PrinterTypeContract(result.get()) : null;
		}

	@Override
	public PrinterTypeContract add(PrinterTypeRequest req) throws ResponseStatusException {
		String printerType = req.type();
		if(StringUtils.isStringNotEmpty(printerType)) {
			if(!repo.existsByType(printerType)) {
				PrinterType persisted = repo.save(new PrinterType(req));
				return new PrinterTypeContract(persisted);
			}
			else {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot add new PrinterType");
			}
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "PrinterType Request is empty");
		}
	}

	@Override
	public PrinterTypeContract edit(Integer identity, PrinterTypeRequest req) throws ResponseStatusException {
		if(repo.existsById(identity)) {
			PrinterType persisted = repo.save(new PrinterType(identity, req));
			return new PrinterTypeContract(persisted);
		}
		else {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot add new PrinterType");
		}
	}

	@Override
	public void remove(Integer identity) {
		repo.deleteById(identity);
	}
}

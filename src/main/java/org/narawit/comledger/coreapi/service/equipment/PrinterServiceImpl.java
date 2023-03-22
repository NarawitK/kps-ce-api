package org.narawit.comledger.coreapi.service.equipment;

import java.util.List;
import java.util.Optional;

import org.narawit.comledger.coreapi.contracts.equipment.PrinterContract;
import org.narawit.comledger.coreapi.contracts.equipment.PrinterRequest;
import org.narawit.comledger.coreapi.domain.equipment.Printer;
import org.narawit.comledger.coreapi.repo.equipment.PrinterRepo;
import org.narawit.comledger.coreapi.service.common.ServiceCommon;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PrinterServiceImpl implements PrinterService{
	private PrinterRepo repo;
	public PrinterServiceImpl(PrinterRepo repo) {
		this.repo = repo;
	}
	@Override
	public Iterable<PrinterContract> findAll() {
		Iterable<Printer> result = repo.findAll();
		List<PrinterContract> contracts = ServiceCommon.AddEntityToContractCollection(result, e -> {
			return new PrinterContract(e);
		});
		return contracts;
	}
	@Override
	public PrinterContract findById(Long identity) {
		Optional<Printer> result = repo.findById(identity);
		return result.isPresent() ? new PrinterContract(result.get()) : null;
	}
	@Override
	public PrinterContract add(PrinterRequest req) throws ResponseStatusException {
		if(!repo.existsByEquipmentId(req.equipmentId())) {
			return persistToDb(req);			
		}
		else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Printer with this equipment ID already exist");
		}
	}
	@Override
	public PrinterContract edit(Long identity, PrinterRequest req) throws ResponseStatusException {
		if(repo.existsById(identity)) {
			return persistToDb(req);
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Printer does not exist");
		}
	}
	@Override
	public void remove(Long identity) {
		repo.deleteById(identity);
	}
	
	private PrinterContract persistToDb(PrinterRequest req) {
		Printer persisted = repo.save(new Printer(req));
		return new PrinterContract(persisted);
	}
}

package org.narawit.comledger.coreapi.contracts.equipment;

import org.narawit.comledger.coreapi.domain.Equipment;
import org.narawit.comledger.coreapi.domain.equipment.Scanner;
import org.narawit.comledger.coreapi.domain.option.Manufacture;

public record ScannerContract(Long id, Equipment equipment, Manufacture manufacture, String model) {
	public ScannerContract(Scanner scanner) {
		this(scanner.getId(), scanner.getEquipment(), scanner.getManufacture(), scanner.getModel());
	}
}

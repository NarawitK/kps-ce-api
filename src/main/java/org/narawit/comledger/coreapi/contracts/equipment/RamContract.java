package org.narawit.comledger.coreapi.contracts.equipment;

import org.narawit.comledger.coreapi.domain.equipment.Ram;
import org.narawit.comledger.coreapi.domain.option.Manufacture;
import org.narawit.comledger.coreapi.domain.option.Unit;

public record RamContract(Long id, Manufacture manufacture, String model, String memoryType, Short capacity, Unit capacityUnit) {
	public RamContract(Ram ram) {
		this(ram.getId(), ram.getManufacture(), ram.getModel(), ram.getMemoryType(), ram.getCapacity(), ram.getCapacityUnit());
	}
}

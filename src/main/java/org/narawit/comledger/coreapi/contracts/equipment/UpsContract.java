package org.narawit.comledger.coreapi.contracts.equipment;

import org.narawit.comledger.coreapi.domain.Equipment;
import org.narawit.comledger.coreapi.domain.equipment.Ups;
import org.narawit.comledger.coreapi.domain.option.Manufacture;
import org.narawit.comledger.coreapi.domain.option.Unit;

public record UpsContract(Long id, 
		Equipment equipment, 
		Manufacture manufacture,
		String model,
		Double capacity,
		Unit capacityUnit) {
	public UpsContract(Ups ups) {
		this(ups.getId(), ups.getEquipment(), ups.getManufacture(), ups.getModel(), ups.getCapacity(), ups.getCapacityUnit());
	}
}

package org.narawit.comledger.coreapi.contracts.equipment;

import org.narawit.comledger.coreapi.domain.Equipment;
import org.narawit.comledger.coreapi.domain.equipments.Vga;
import org.narawit.comledger.coreapi.domain.option.Manufacture;
import org.narawit.comledger.coreapi.domain.option.Unit;

public record VgaContract(Long id, 
		Equipment equipment, 
		Manufacture manufacture, 
		String model, 
		Double memory, 
		Unit memoryUnit) {
	public VgaContract(Vga entity) {
		this(entity.getId(), 
				entity.getEquipment(), 
				entity.getManufacture(), 
				entity.getModel(), 
				entity.getMemory(), 
				entity.getMemoryUnit());
	}
}

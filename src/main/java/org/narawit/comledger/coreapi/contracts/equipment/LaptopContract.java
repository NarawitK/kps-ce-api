package org.narawit.comledger.coreapi.contracts.equipment;

import org.narawit.comledger.coreapi.domain.Equipment;
import org.narawit.comledger.coreapi.domain.equipments.Cpu;
import org.narawit.comledger.coreapi.domain.equipments.Laptop;
import org.narawit.comledger.coreapi.domain.equipments.Ram;
import org.narawit.comledger.coreapi.domain.option.CdromType;
import org.narawit.comledger.coreapi.domain.option.Manufacture;

public record LaptopContract(
		Equipment equipment,
		Manufacture manufacture,
		String model,
		String pointerDevice,
		String keyboard,
		CdromType cdRomTypeId,
		Cpu cpu,
		Ram ram
		) {
	public LaptopContract(Laptop laptop) {
		this(
				laptop.getEquipment(), 
				laptop.getManufacture(), 
				laptop.getModel(), 
				laptop.getPointerDevice(),
				laptop.getKeyboard(), 
				laptop.getCdromType(), 
				laptop.getCpu(), 
				laptop.getRam());
	}
}

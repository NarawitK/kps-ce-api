package org.narawit.comledger.coreapi.contracts.equipment;

import org.narawit.comledger.coreapi.domain.Equipment;
import org.narawit.comledger.coreapi.domain.equipment.Cpu;
import org.narawit.comledger.coreapi.domain.equipment.Nic;
import org.narawit.comledger.coreapi.domain.equipment.Pc;
import org.narawit.comledger.coreapi.domain.equipment.Ram;
import org.narawit.comledger.coreapi.domain.equipment.Ups;
import org.narawit.comledger.coreapi.domain.equipment.Vga;
import org.narawit.comledger.coreapi.domain.option.CdromType;
import org.narawit.comledger.coreapi.domain.option.Manufacture;
import org.narawit.comledger.coreapi.domain.option.Unit;

public record PcContract(
		Long id,
		Equipment equipment,
		Manufacture mainboardManufacture,
		String mainboardModel,
		String pointerDevice,
		String keyboard,
		Cpu cpu,
		Ram ram,
		CdromType cdromType,
		Manufacture psuManufacture,
		Double psuPower,
		Unit psuUnit,
		Nic nic,
		Ups ups,
		Vga vga
		) {
	public PcContract(Pc pc) {
		this(
				pc.getId(), 
				pc.getEquipment(), 
				pc.getMbManufacture(), 
				pc.getMbModel(), 
				pc.getPointerDevice(), 
				pc.getKeyboard(), 
				pc.getCpu(), 
				pc.getRam(), 
				pc.getCdromType(), 
				pc.getPsuManufacture(), 
				pc.getPsuPower(), 
				pc.getPsuUnit(), 
				pc.getNic(), 
				pc.getUps(), 
				pc.getVga());
	}
}

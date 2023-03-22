package org.narawit.comledger.coreapi.contracts.equipment;

import java.time.ZonedDateTime;

import org.narawit.comledger.coreapi.domain.Equipment;
import org.narawit.comledger.coreapi.domain.equipment.Printer;
import org.narawit.comledger.coreapi.domain.option.Manufacture;
import org.narawit.comledger.coreapi.domain.option.PrinterType;

public record PrinterContract(
		Long id, 
		Equipment equipment, 
		Manufacture manufacture, 
		PrinterType printerType,
		String model,
		String inkModel,
		ZonedDateTime lastInkReloadDate) {
	public PrinterContract(Printer printer) {
		this(
				printer.getId(), 
				printer.getEquipment(), 
				printer.getManufacture(), 
				printer.getPrinterType(), 
				printer.getModel(), 
				printer.getInkModel(), 
				printer.getLastInkReloadDate()
			);
	}
}

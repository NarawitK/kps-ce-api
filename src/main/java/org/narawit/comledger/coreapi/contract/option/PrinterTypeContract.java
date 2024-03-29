package org.narawit.comledger.coreapi.contract.option;

import org.narawit.comledger.coreapi.domain.option.PrinterType;

public record PrinterTypeContract(Integer id, String type) {
	public PrinterTypeContract(PrinterType printerType) {
		this(printerType.getId(), printerType.getType());
	}
}

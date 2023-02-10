package org.narawit.comledger.coreapi.contracts.equipment;

import org.narawit.comledger.coreapi.domain.Equipment;
import org.narawit.comledger.coreapi.domain.equipments.Nic;
import org.narawit.comledger.coreapi.domain.option.Manufacture;
import org.narawit.comledger.coreapi.domain.option.NetworkConnectionType;
import org.narawit.comledger.coreapi.domain.option.NetworkConnectorType;
import org.narawit.comledger.coreapi.domain.option.Unit;

public record NicContract(
		Long id, 
		Equipment equipment, 
		Manufacture manufacture,
		NetworkConnectionType connectionType,
		NetworkConnectorType connectorType,
		String model,
		Double speed,
		Unit speedUnit) {
	public NicContract(Nic nic) {
		this(
				nic.getId(), 
				nic.getEquipment(), 
				nic.getManufacture(), 
				nic.getConnectionType(), 
				nic.getConnectorType(), 
				nic.getModel(), 
				nic.getSpeed(), 
				nic.getSpeedUnit()
			);
	}

}

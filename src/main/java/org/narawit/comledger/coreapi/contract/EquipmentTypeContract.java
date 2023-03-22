package org.narawit.comledger.coreapi.contract;

import org.narawit.comledger.coreapi.domain.EquipmentType;

public record EquipmentTypeContract(Long id, String name) {
	public EquipmentTypeContract(EquipmentType entity) {
		this(entity.getId(), entity.getName());
	}
}

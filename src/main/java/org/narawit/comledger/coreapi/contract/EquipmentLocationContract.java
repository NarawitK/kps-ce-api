package org.narawit.comledger.coreapi.contract;

import java.time.ZonedDateTime;

import org.narawit.comledger.coreapi.domain.EquipmentLocation;

public record EquipmentLocationContract(Long id, DepartmentContract department, SubDepartmentContract subdepartment, EquipmentContract equipment, ZonedDateTime assignedDate, boolean active, ZonedDateTime lastDeactiveDate) {
	public EquipmentLocationContract(EquipmentLocation entity) {
		this(
				entity.getId(),
				new DepartmentContract(entity.getDepartment()),
				new SubDepartmentContract(entity.getSubDepartment()),
				new EquipmentContract(entity.getEquipment()),
				entity.getAssigned_date(),
				entity.getActive(),
				entity.getLastDeactiveDate()
			);
	}
}
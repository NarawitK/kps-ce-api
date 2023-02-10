package org.narawit.comledger.coreapi.contracts;

import java.time.ZonedDateTime;

import org.narawit.comledger.coreapi.domain.EquipmentLocation;

public record EquipmentLocationContract(Integer deptId, Integer subDeptId, Long equipmentId, ZonedDateTime assignedDate, boolean active, ZonedDateTime lastDeactiveDate) {
	public EquipmentLocationContract(EquipmentLocation entity) {
		this(
				entity.getDepartment().getId(), 
				entity.getSubDepartment().getId(),
				entity.getEquipment().getId(),
				entity.getAssigned_date(),
				entity.getActive(),
				entity.getLastDeactiveDate()
			);
	}
}
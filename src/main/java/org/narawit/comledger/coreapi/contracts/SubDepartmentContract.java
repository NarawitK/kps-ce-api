package org.narawit.comledger.coreapi.contracts;

import org.narawit.comledger.coreapi.domain.SubDepartment;

public record SubDepartmentContract(Integer deptId, String name, boolean active) {
	public SubDepartmentContract(SubDepartment subdept) {
		this(subdept.getDepartment().getId(), subdept.getName(), subdept.getActive());
	}
}
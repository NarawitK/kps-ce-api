package org.narawit.comledger.coreapi.contract;

import org.narawit.comledger.coreapi.domain.SubDepartment;

public record SubDepartmentContract(DepartmentContract dept, String name, boolean active) {
	public SubDepartmentContract(SubDepartment subdept) {
		this(new DepartmentContract(subdept.getDepartment()), subdept.getName(), subdept.getActive());
	}
}
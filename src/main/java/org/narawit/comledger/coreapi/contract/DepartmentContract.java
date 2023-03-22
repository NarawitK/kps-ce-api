package org.narawit.comledger.coreapi.contract;

import org.narawit.comledger.coreapi.domain.Department;

public record DepartmentContract(Integer id, String name, boolean active) {	
	public DepartmentContract(Department dept) {
		this(dept.getId(), dept.getName(), dept.getActive());
	}
}

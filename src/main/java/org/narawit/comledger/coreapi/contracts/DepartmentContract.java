package org.narawit.comledger.coreapi.contracts;

import org.narawit.comledger.coreapi.domain.Department;

public record DepartmentContract(String name, boolean active) {	
	public DepartmentContract(Department dept) {
		this(dept.getName(), dept.getActive());
	}
}

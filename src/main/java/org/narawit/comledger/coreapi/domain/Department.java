package org.narawit.comledger.coreapi.domain;

import java.util.Set;

import org.narawit.comledger.coreapi.contract.DepartmentRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "dept", schema = "public", indexes = {
		@Index(columnList = "id", name = "dept_pkey_idx"),
		@Index(columnList = "id, active", name = "dept_pkey_isa_cidx")
})
public class Department {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 150, nullable = false)
	private String name;
	
	@Column(name = "active", columnDefinition = "boolean DEFAULT true", nullable = false)
	private boolean active;
	
	// Foreign Key in Other Entities
	@OneToMany(mappedBy = "department")
	private Set<EquipmentLocation>  equipmentLocations;
	
	@OneToMany(mappedBy = "department")
	private Set<SubDepartment> subDepartments;
	
	@OneToOne(mappedBy = "user")
	private RefreshToken refreshToken;
	
	public Department() {}
	
	public Department(DepartmentRequest req) {
		this.name = req.name();
		this.active = req.active();
	}
	
	public Department(Integer id, DepartmentRequest req) {
		this.id = id;
		this.name = req.name();
		this.active = req.active();
	}
	
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean isActive) {
		this.active = isActive;
	}
}

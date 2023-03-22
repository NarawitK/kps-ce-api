package org.narawit.comledger.coreapi.domain;

import java.util.Set;

import org.narawit.comledger.coreapi.contract.SubDepartmentRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "sub_dept", schema = "public", indexes = {
		@Index(columnList = "id", name = "subdept_pkey_idx"),
		@Index(columnList = "dept_id", name = "subdept_fk_idx")
})
public class SubDepartment {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 150, nullable = false)
	private String name;
	@Column(name = "active", nullable = false)
	private boolean active;
	
	// Foreign Key Declarations
	@ManyToOne
	@JoinColumn(name = "dept_id", nullable = false)
	private Department department;
	
	@OneToMany(mappedBy = "subDepartment")
	private Set<EquipmentLocation> equipmentLocations;

	public SubDepartment() {}
	
	public SubDepartment(SubDepartmentRequest req) {
		this.name = req.name();
		this.department.setId(req.deptId());
		this.active = req.active();
	}
	
	public SubDepartment(Integer id, SubDepartmentRequest req) {
		this.id = id;
		this.name = req.name();
		this.department.setId(req.deptId());
		this.active = req.active();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<EquipmentLocation> getEquipmentLocations() {
		return equipmentLocations;
	}

	public void setEquipmentLocations(Set<EquipmentLocation> equipmentLocations) {
		this.equipmentLocations = equipmentLocations;
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

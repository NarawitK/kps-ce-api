package org.narawit.comledger.coreapi.domain;

import java.time.ZonedDateTime;

import org.narawit.comledger.coreapi.contract.EquipmentLocationRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipment_location", schema = "public", indexes = {
		@Index(columnList = "id", name = "eq_loc_pkey_idx"),
		@Index(columnList = "id, active", name = "eq_loc_pkey_isa_cidx"),
		@Index(columnList = "equipment_id", name = "eq_loc_equip_id_idx")
})
public class EquipmentLocation {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private ZonedDateTime assigned_date;
	@Column(name = "active", columnDefinition = "boolean DEFAULT true", nullable = false)
	private boolean active;
	@Column(name = "last_deactive_date")
	private ZonedDateTime lastDeactiveDate;
	
	// Foreign Key Declarations
	@ManyToOne
	@JoinColumn(name = "dept_id", nullable = false)
	private Department department;
	@ManyToOne
	@JoinColumn(name = "dept_sub_id", nullable = false)
	private SubDepartment subDepartment;
	@ManyToOne
	@JoinColumn(name = "equipment_id", nullable = false)
	private Equipment equipment;

	public EquipmentLocation() {}
	
	//Request-Based Constructor
	public EquipmentLocation(Long id, EquipmentLocationRequest req) {
		this.id = id;
		this.assigned_date = req.assignedDate();
		this.active = req.active();
		this.lastDeactiveDate = req.lastDeactiveDate();
		this.department = null;
		this.subDepartment = null;
		this.equipment = null;
		if(req.deptId() != null) { 
			this.department = new Department();
			this.department.setId(req.deptId());
		}
		if(req.subDeptId() != null) { 
			this.subDepartment = new SubDepartment();
			this.subDepartment.setId(req.subDeptId());
		}
		if(req.equipmentId() != null) { 
			this.equipment = new Equipment();
			this.equipment.setId(req.equipmentId());
		}
	}
	
	//Request-Based Constructor
	public EquipmentLocation(EquipmentLocationRequest req) {
		this.id = null;
		this.assigned_date = req.assignedDate();
		this.active = req.active();
		this.lastDeactiveDate = req.lastDeactiveDate();
		this.department = null;
		this.subDepartment = null;
		this.equipment = null;
		if(req.deptId() != null) { 
			this.department = new Department();
			this.department.setId(req.deptId());
		}
		if(req.subDeptId() != null) { 
			this.subDepartment = new SubDepartment();
			this.subDepartment.setId(req.subDeptId());
		}
		if(req.equipmentId() != null) { 
			this.equipment = new Equipment();
			this.equipment.setId(req.equipmentId());
		}
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public SubDepartment getSubDepartment() {
		return subDepartment;
	}

	public void setSubDepartment(SubDepartment subDepartment) {
		this.subDepartment = subDepartment;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public ZonedDateTime getAssigned_date() {
		return assigned_date;
	}

	public void setAssigned_date(ZonedDateTime assigned_date) {
		this.assigned_date = assigned_date;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean isActive) {
		this.active = isActive;
	}

	public ZonedDateTime getLastDeactiveDate() {
		return lastDeactiveDate;
	}

	public void setLastDeactiveDate(ZonedDateTime lastDeactiveDate) {
		this.lastDeactiveDate = lastDeactiveDate;
	}
}

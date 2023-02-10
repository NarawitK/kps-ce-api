package org.narawit.comledger.coreapi.domain;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
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
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dept_id_generator")
	// @SequenceGenerator(name = "dept_id_generator", sequenceName = "dept_id_seq", allocationSize = 1)
	private Integer id;
	@Column(length = 150, nullable = false)
	private String name;
	@Column(name = "active", columnDefinition = "boolean DEFAULT true", nullable = false)
	private boolean active;
	
	// Foreign Key Declarations
	@OneToMany(mappedBy = "department")
	private Set<EquipmentLocation>  equipmentLocations;
	@OneToMany(mappedBy = "department")
	private Set<SubDepartment> subDepartments;
	
	public Department() {}
	public Department(String name, boolean isActive) {
		this.name = name;
		this.active = isActive;
	}
	public Department(int id, String name, boolean isActive) {
		this.id = id;
		this.name = name;
		this.active = isActive;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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

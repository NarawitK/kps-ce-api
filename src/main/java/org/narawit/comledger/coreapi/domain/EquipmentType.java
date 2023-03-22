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
@Table(name = "equipment_type", schema = "public", 
indexes = @Index(columnList = "id", name = "equipment_type_pkey_idx"))
public class EquipmentType {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 50, nullable = false)
	private String name;
	@OneToMany(mappedBy = "equipmentType")
	private Set<Equipment> equipments;
	
	public EquipmentType() {}
	public EquipmentType(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	public EquipmentType(String name) {
		this.id = null;
		this.name = name;
	}
	
	public Set<Equipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(Set<Equipment> equipments) {
		this.equipments = equipments;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

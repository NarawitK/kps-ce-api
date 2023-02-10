package org.narawit.comledger.coreapi.domain.equipments;

import org.narawit.comledger.coreapi.domain.Equipment;
import org.narawit.comledger.coreapi.domain.option.Manufacture;
import org.narawit.comledger.coreapi.domain.option.Unit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ups", schema = "equipment_detail", indexes = {
		@Index(columnList = "id", name = "ups_pkey_idx"),
		@Index(columnList = "equipment_id", name = "ups_eq_fk_idx")
})
public class Ups {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "equipment_id", nullable = false)
	private Equipment equipment;
	@ManyToOne
	@JoinColumn(name = "manufacture_id", nullable = false)
	private Manufacture manufacture;
	@Column(length = 20, nullable = false)
	private String model;
	@Column(nullable = false)
	private Double capacity;
	@ManyToOne
	@JoinColumn(name = "capacity_unit_id", nullable = false)
	private Unit capacityUnit;
	
	@OneToOne(mappedBy = "ups")
	private Pc pc;
	/*
	@ManyToOne
	@JoinColumn(name = "pc_id")
	private Pc pc;
	 */
	
	public Ups() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Pc getPc() {
		return pc;
	}

	public void setPc(Pc pc) {
		this.pc = pc;
	}

	public Manufacture getManufacture() {
		return manufacture;
	}

	public void setManufacture(Manufacture manufacture) {
		this.manufacture = manufacture;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Double getCapacity() {
		return capacity;
	}

	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}

	public Unit getCapacityUnit() {
		return capacityUnit;
	}

	public void setCapacityUnit(Unit capacityUnit) {
		this.capacityUnit = capacityUnit;
	}
}

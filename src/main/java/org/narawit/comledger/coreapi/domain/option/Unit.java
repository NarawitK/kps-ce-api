package org.narawit.comledger.coreapi.domain.option;

import java.util.Set;

import org.narawit.comledger.coreapi.contract.option.UnitRequest;
import org.narawit.comledger.coreapi.domain.equipment.Nic;
import org.narawit.comledger.coreapi.domain.equipment.Pc;
import org.narawit.comledger.coreapi.domain.equipment.Ups;
import org.narawit.comledger.coreapi.domain.equipment.Vga;

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
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "unit", schema = "option", 
indexes = @Index(columnList = "id", name = "unit_pkey_idx"),
uniqueConstraints = @UniqueConstraint(columnNames = "unit", name = "unit_name_U"))
public class Unit {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 10, nullable = false, unique = true)
	private String unit;

	@ManyToOne
	@JoinColumn(name = "type_id", nullable = true)
	private UnitType unitType;
	
	@OneToMany(mappedBy = "psuUnit")
	private Set<Pc> pcPsuUnits;
	// NIC Entity
	@OneToMany(mappedBy = "speedUnit")
	private Set<Nic> nicSpeedUnits;
	// UPS Entity
	@OneToMany(mappedBy = "capacityUnit")
	private Set<Ups> upsCapacityUnits;
	// VGA ENTITY
	@OneToMany(mappedBy = "memoryUnit")
	private Set<Vga> vgaMemoryUnits;
	
	public Unit() {}
	public Unit(int id) {
		this.id = id;
	}
	public Unit(int id, UnitRequest req) {
		this.id = id;
		this.unit = req.unit();
	}
	public Unit(UnitRequest req) {
		this.unit = req.unit();
		this.unitType = new UnitType(req.unitTypeId());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public UnitType getUnitType() {
		return unitType;
	}

	public void setUnitType(UnitType unitType) {
		this.unitType = unitType;
	}
	
	public Set<Pc> getPcPsuUnits() {
		return pcPsuUnits;
	}

	public void setPcPsuUnits(Set<Pc> pcPsuUnits) {
		this.pcPsuUnits = pcPsuUnits;
	}

	public Set<Nic> getNicSpeedUnits() {
		return nicSpeedUnits;
	}

	public void setNicSpeedUnits(Set<Nic> nicSpeedUnits) {
		this.nicSpeedUnits = nicSpeedUnits;
	}

	public Set<Ups> getUpsCapacityUnits() {
		return upsCapacityUnits;
	}

	public void setUpsCapacityUnits(Set<Ups> upsCapacityUnits) {
		this.upsCapacityUnits = upsCapacityUnits;
	}

	public Set<Vga> getVgaMemoryUnits() {
		return vgaMemoryUnits;
	}

	public void setVgaMemoryUnits(Set<Vga> vgaMemoryUnits) {
		this.vgaMemoryUnits = vgaMemoryUnits;
	}
}

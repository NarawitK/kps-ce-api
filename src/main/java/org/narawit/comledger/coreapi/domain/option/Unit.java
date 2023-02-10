package org.narawit.comledger.coreapi.domain.option;

import java.util.Set;

import org.narawit.comledger.coreapi.domain.equipments.Laptop;
import org.narawit.comledger.coreapi.domain.equipments.Nic;
import org.narawit.comledger.coreapi.domain.equipments.Pc;
import org.narawit.comledger.coreapi.domain.equipments.Ups;
import org.narawit.comledger.coreapi.domain.equipments.Vga;

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
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unit_id_gen")
	// @SequenceGenerator(name = "unit_id_gen", sequenceName = "unit_id_seq", allocationSize = 1)
	private Integer id;
	@Column(length = 10, nullable = false, unique = true)
	private String unit;

	@ManyToOne
	@JoinColumn(name = "type_id", nullable = true)
	private TypeOfUnit unitType;
	
	// Laptop Entity
	@OneToMany(mappedBy = "cpuSpeedUnit")
	private Set<Laptop> laptopCpuSpeedUnits;
	@OneToMany(mappedBy = "ramCapacityUnit")
	private Set<Laptop> laptopRamCapacityUnits;
	// PC Entity
	@OneToMany(mappedBy = "cpuSpeedUnit")
	private Set<Pc> pcCpuSpeedUnits;
	@OneToMany(mappedBy = "ramCapacityUnit")
	private Set<Pc> pcRamSpeedUnits;
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

	public TypeOfUnit getUnitType() {
		return unitType;
	}

	public void setUnitType(TypeOfUnit unitType) {
		this.unitType = unitType;
	}

	public Set<Laptop> getLaptopCpuSpeedUnits() {
		return laptopCpuSpeedUnits;
	}

	public void setLaptopCpuSpeedUnits(Set<Laptop> laptopCpuSpeedUnits) {
		this.laptopCpuSpeedUnits = laptopCpuSpeedUnits;
	}

	public Set<Laptop> getLaptopRamCapacityUnits() {
		return laptopRamCapacityUnits;
	}

	public void setLaptopRamCapacityUnits(Set<Laptop> laptopRamCapacityUnits) {
		this.laptopRamCapacityUnits = laptopRamCapacityUnits;
	}

	public Set<Pc> getPcCpuSpeedUnits() {
		return pcCpuSpeedUnits;
	}

	public void setPcCpuSpeedUnits(Set<Pc> pcCpuSpeedUnits) {
		this.pcCpuSpeedUnits = pcCpuSpeedUnits;
	}

	public Set<Pc> getPcRamSpeedUnits() {
		return pcRamSpeedUnits;
	}

	public void setPcRamSpeedUnits(Set<Pc> pcRamSpeedUnits) {
		this.pcRamSpeedUnits = pcRamSpeedUnits;
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

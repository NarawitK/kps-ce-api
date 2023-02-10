package org.narawit.comledger.coreapi.domain;

import java.time.ZonedDateTime;
import java.util.Set;

import org.narawit.comledger.coreapi.domain.equipments.*;

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
@Table(name = "equipment", schema = "public", indexes = {
		@Index(columnList = "id", name = "equipment_pkey_idx"),
		@Index(columnList = "id, active", name = "equipment_pkey_isa_cidx"),
		@Index(columnList = "equipment_type_id", name = "equipment_type_fk_idx")
})
public class Equipment {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 50, nullable = false)
	private String name;
	@Column(length = 250)
	private String description;
	@Column(name = "internal_identifier", nullable = false)
	private String internalIdentifier;
	@Column(name = "serial_number")
	private String serialNumber;
	@Column(name = "active", columnDefinition = "boolean DEFAULT false", nullable = false)
	private boolean active;
	@Column(name = "import_date")
	private ZonedDateTime importDate;
	@Column(name = "register_date", columnDefinition = "timestamp with time zone DEFAULT NOW()", nullable = false)
	private ZonedDateTime registerDate;
	@Column(name = "update_date", columnDefinition = "timestamp with time zone DEFAULT NOW()", nullable = false)
	private ZonedDateTime updateDate;
	
	// Foreign Key Declaration
	@ManyToOne
	@JoinColumn(name = "equipment_type_id", nullable = false)
	private EquipmentType equipmentType;
	
	// Reference Key
	@OneToMany(mappedBy = "equipment")
	private Set<MaintenanceHistory> equipments;
	@OneToMany(mappedBy = "equipment")
	private Set<Laptop> laptops;
	@OneToMany(mappedBy = "equipment")
	private Set<Pc> pcs;
	@OneToMany(mappedBy = "equipment")
	private Set<Nic> nics;
	@OneToMany(mappedBy = "equipment")
	private Set<Printer> printers;
	@OneToMany(mappedBy = "equipment")
	private Set<Scanner> scanners;
	@OneToMany(mappedBy = "equipment")
	private Set<Ups> upses;
	@OneToMany(mappedBy = "equipment")
	private Set<Vga> vgas;
	
	
	public Equipment() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getInternalIdentifier() {
		return internalIdentifier;
	}

	public void setInternalIdentifier(String internalIdentifier) {
		this.internalIdentifier = internalIdentifier;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean isActive) {
		this.active = isActive;
	}

	public ZonedDateTime getImportDate() {
		return importDate;
	}

	public void setImportDate(ZonedDateTime importDate) {
		this.importDate = importDate;
	}

	public ZonedDateTime getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(ZonedDateTime registerDate) {
		this.registerDate = registerDate;
	}

	public ZonedDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(ZonedDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public EquipmentType getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(EquipmentType equipmentType) {
		this.equipmentType = equipmentType;
	}

	public Set<MaintenanceHistory> getEquipments() {
		return equipments;
	}

	public void setEquipments(Set<MaintenanceHistory> equipments) {
		this.equipments = equipments;
	}

	public Set<Laptop> getLaptops() {
		return laptops;
	}

	public void setLaptops(Set<Laptop> laptops) {
		this.laptops = laptops;
	}

	public Set<Pc> getPcs() {
		return pcs;
	}

	public void setPcs(Set<Pc> pcs) {
		this.pcs = pcs;
	}


	public Set<Nic> getNics() {
		return nics;
	}

	public void setNics(Set<Nic> nics) {
		this.nics = nics;
	}

	public Set<Printer> getPrinters() {
		return printers;
	}

	public void setPrinters(Set<Printer> printers) {
		this.printers = printers;
	}

	public Set<Scanner> getScanners() {
		return scanners;
	}

	public void setScanners(Set<Scanner> scanners) {
		this.scanners = scanners;
	}

	public Set<Ups> getUpses() {
		return upses;
	}

	public void setUpses(Set<Ups> upses) {
		this.upses = upses;
	}

	public Set<Vga> getVgas() {
		return vgas;
	}

	public void setVgas(Set<Vga> vgas) {
		this.vgas = vgas;
	}
	
}

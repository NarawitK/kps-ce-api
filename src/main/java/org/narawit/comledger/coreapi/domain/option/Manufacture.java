package org.narawit.comledger.coreapi.domain.option;

import java.util.Set;

import org.narawit.comledger.coreapi.domain.equipments.Laptop;
import org.narawit.comledger.coreapi.domain.equipments.Nic;
import org.narawit.comledger.coreapi.domain.equipments.Pc;
import org.narawit.comledger.coreapi.domain.equipments.Printer;
import org.narawit.comledger.coreapi.domain.equipments.Scanner;
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

@Entity
@Table(name = "manufacture", schema = "option", indexes = @Index(columnList = "id", name = "manufacture_pkey_idx"))
public class Manufacture {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 30, nullable = false)
	private String name;
	
	// Reference Keys Declaration
	// LAPTOP
	@OneToMany(mappedBy = "manufacture")
	private Set<Laptop> laptops;
	@OneToMany(mappedBy = "cpuManufacture")
	private Set<Laptop> laptopsCpuManufacture;
	@OneToMany(mappedBy = "ramManufacture")
	private Set<Laptop> laptopsRamManufacture;
	// PC
	@OneToMany(mappedBy = "mbManufacture")
	private Set<Pc> pcsMainboard;
	@OneToMany(mappedBy = "cpuManufacture")
	private Set<Pc> pcsCpuManufacture;
	@OneToMany(mappedBy = "ramManufacture")
	private Set<Pc> pcsRamManufacture;
	@OneToMany(mappedBy = "psuManufacture")
	private Set<Pc> psuManufacture;
	// NICS
	@OneToMany(mappedBy = "manufacture")
	private Set<Nic> nics;
	// PRINTER
	@OneToMany(mappedBy = "manufacture")
	private Set<Printer> printers;
	// SCANNER
	@OneToMany(mappedBy = "manufacture")
	private Set<Scanner> scanners;
	// UPS
	@OneToMany(mappedBy = "manufacture")
	private Set<Ups> upses;
	
	@OneToMany(mappedBy = "manufacture")
	private Set<Vga> vgas;
	
	// Foreign Key Declaration
	@ManyToOne
	@JoinColumn(name = "manufacture_category_id", nullable = true)
	private ManufactureCategory manufactureCategory;
	
	public Manufacture() {}

	public Integer getId() {
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

	public Set<Laptop> getLaptops() {
		return laptops;
	}

	public void setLaptops(Set<Laptop> laptops) {
		this.laptops = laptops;
	}

	public Set<Laptop> getLaptopsCpuManufacture() {
		return laptopsCpuManufacture;
	}

	public void setLaptopsCpuManufacture(Set<Laptop> laptopsCpuManufacture) {
		this.laptopsCpuManufacture = laptopsCpuManufacture;
	}

	public Set<Laptop> getLaptopsRamManufacture() {
		return laptopsRamManufacture;
	}

	public void setLaptopsRamManufacture(Set<Laptop> laptopsRamManufacture) {
		this.laptopsRamManufacture = laptopsRamManufacture;
	}

	public Set<Pc> getPcsMainboard() {
		return pcsMainboard;
	}

	public void setPcsMainboard(Set<Pc> pcsMainboard) {
		this.pcsMainboard = pcsMainboard;
	}

	public Set<Pc> getPcsCpuManufacture() {
		return pcsCpuManufacture;
	}

	public void setPcsCpuManufacture(Set<Pc> pcsCpuManufacture) {
		this.pcsCpuManufacture = pcsCpuManufacture;
	}

	public Set<Pc> getPcsRamManufacture() {
		return pcsRamManufacture;
	}

	public void setPcsRamManufacture(Set<Pc> pcsRamManufacture) {
		this.pcsRamManufacture = pcsRamManufacture;
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

	public ManufactureCategory getManufactureCategory() {
		return manufactureCategory;
	}

	public void setManufactureCategory(ManufactureCategory manufactureCategory) {
		this.manufactureCategory = manufactureCategory;
	}
}

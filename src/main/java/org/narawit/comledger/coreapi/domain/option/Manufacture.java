package org.narawit.comledger.coreapi.domain.option;

import java.util.Set;

import org.narawit.comledger.coreapi.contract.option.ManufactureRequest;
import org.narawit.comledger.coreapi.domain.equipment.Laptop;
import org.narawit.comledger.coreapi.domain.equipment.Nic;
import org.narawit.comledger.coreapi.domain.equipment.Pc;
import org.narawit.comledger.coreapi.domain.equipment.Printer;
import org.narawit.comledger.coreapi.domain.equipment.Scanner;
import org.narawit.comledger.coreapi.domain.equipment.Ups;
import org.narawit.comledger.coreapi.domain.equipment.Vga;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	
	// Foreign Key.
	@ManyToOne
	@JoinColumn(name = "manufacture_category_id", nullable = true)
	private ManufactureCategory manufactureCategory;
	
	// Key referenced from other tables.
	// LAPTOP
	@OneToMany(mappedBy = "manufacture", fetch = FetchType.LAZY)
	private Set<Laptop> laptops;
	// PC
	@OneToMany(mappedBy = "mbManufacture", fetch = FetchType.LAZY)
	private Set<Pc> pcsMainboard;
	@OneToMany(mappedBy = "psuManufacture", fetch = FetchType.LAZY)
	private Set<Pc> psuManufacture;
	// NICS
	@OneToMany(mappedBy = "manufacture", fetch = FetchType.LAZY)
	private Set<Nic> nics;
	// PRINTER
	@OneToMany(mappedBy = "manufacture", fetch = FetchType.LAZY)
	private Set<Printer> printers;
	// SCANNER
	@OneToMany(mappedBy = "manufacture", fetch = FetchType.LAZY)
	private Set<Scanner> scanners;
	// UPS
	@OneToMany(mappedBy = "manufacture", fetch = FetchType.LAZY)
	private Set<Ups> upses;
	
	@OneToMany(mappedBy = "manufacture", fetch = FetchType.LAZY)
	private Set<Vga> vgas;
	
	
	public Manufacture() {}
	
	public Manufacture(Integer id) {
		this.id = id;
	}
	
	public Manufacture(ManufactureRequest req) {
		this.name = req.name();
	}
	
	public Manufacture(Integer id, ManufactureRequest req) {
		this.id = id;
		this.name = req.name();
	}

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

	public Set<Pc> getPcsMainboard() {
		return pcsMainboard;
	}

	public void setPcsMainboard(Set<Pc> pcsMainboard) {
		this.pcsMainboard = pcsMainboard;
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

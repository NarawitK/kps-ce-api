package org.narawit.comledger.coreapi.domain.equipment;

import org.narawit.comledger.coreapi.contracts.equipment.RamRequest;
import org.narawit.comledger.coreapi.domain.option.Manufacture;
import org.narawit.comledger.coreapi.domain.option.Unit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ram", schema = "equipment_detail")
public class Ram {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "manufacture_id", nullable = false)
	private Manufacture manufacture;
	@Column(length =  30, nullable = false)
	private String model;
	// Type like: DDR(x)
	@Column(name = "memory_type", length = 10)
	private String memoryType;
	private Short capacity;
	
	@ManyToOne
	@JoinColumn(name = "capacity_unit_id", nullable = true)
	private Unit capacityUnit;
	
	@OneToOne(mappedBy = "ram")
	private Laptop laptop;
	@OneToOne(mappedBy = "ram")
	private Pc pc;
	
	public Ram() {}
	
	public Ram(Long id) {
		this.id = id;
	}
	
	public Ram(RamRequest req) {
		this.manufacture = new Manufacture(req.manufactureId());
		this.model = req.model();
		this.memoryType = req.memoryType();
		this.capacity = req.capacity();
		this.capacityUnit = new Unit(req.capacityUnitId());
	}
	
	public Ram(Long id, RamRequest req) {
		this.id = id;
		this.manufacture = new Manufacture(req.manufactureId());
		this.model = req.model();
		this.memoryType = req.memoryType();
		this.capacity = req.capacity();
		this.capacityUnit = new Unit(req.capacityUnitId());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getMemoryType() {
		return memoryType;
	}
	public void setMemoryType(String memoryType) {
		this.memoryType = memoryType;
	}
	public Short getCapacity() {
		return capacity;
	}
	public void setCapacity(Short capacity) {
		this.capacity = capacity;
	}
	public Unit getCapacityUnit() {
		return capacityUnit;
	}
	public void setCapacityUnit(Unit capacityUnit) {
		this.capacityUnit = capacityUnit;
	}
	public Laptop getLaptop() {
		return laptop;
	}
	public void setLaptop(Laptop laptop) {
		this.laptop = laptop;
	}
	public Pc getPc() {
		return pc;
	}
	public void setPc(Pc pc) {
		this.pc = pc;
	}
}

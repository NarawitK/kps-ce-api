package org.narawit.comledger.coreapi.domain.equipment;

import org.narawit.comledger.coreapi.contracts.equipment.VgaRequest;
import org.narawit.comledger.coreapi.domain.Equipment;
import org.narawit.comledger.coreapi.domain.option.Manufacture;
import org.narawit.comledger.coreapi.domain.option.Unit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vga", schema = "equipment_detail", indexes = {
		@Index(columnList = "id", name = "vga_pkey_idx"),
		@Index(columnList = "equipment_id", name = "vga_eq_fk_idx")
})
public class Vga {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "equipment_id", nullable = false)
	private Equipment equipment;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manufacture_id", nullable = false)
	private Manufacture manufacture;
	@Column(length = 20, nullable = false)
	private String model;
	@Column(nullable = false)
	private Double memory;
	@ManyToOne
	@JoinColumn(name = "memory_unit_id", nullable = false)
	private Unit memoryUnit;
	
	@OneToOne(mappedBy = "vga")
	private Pc pc;
	
	public Vga() {}
	
	public Vga(Long id) {
		this.id = id;
	}
	
	public Vga(VgaRequest req) {
		this.equipment = new Equipment(req.equipmentId());
		this.manufacture = new Manufacture(req.manufactureId());
		this.model = req.model();
		this.memory = req.memory();
		this.memoryUnit = new Unit(req.memoryUnitId());
	}
	
	public Vga(Long id, VgaRequest req) {
		this(req);
		this.id = id;
	}

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

	public Double getMemory() {
		return memory;
	}

	public void setMemory(Double memory) {
		this.memory = memory;
	}

	public Unit getMemoryUnit() {
		return memoryUnit;
	}

	public void setMemoryUnit(Unit memoryUnit) {
		this.memoryUnit = memoryUnit;
	}
}

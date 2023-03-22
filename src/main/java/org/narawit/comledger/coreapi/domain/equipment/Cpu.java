package org.narawit.comledger.coreapi.domain.equipment;

import org.narawit.comledger.coreapi.contracts.equipment.CpuRequest;
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
@Table(name = "cpu", schema = "equipment_detail")
public class Cpu {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length =  30, nullable = false)
	private String model;
	@Column(name = "thread_count")
	private Short threadCount;
	@Column(name = "clock_speed")
	private Double clockSpeed;
	@ManyToOne
	@JoinColumn(name = "manufacture_id", nullable = false)
	private Manufacture manufacture;
	@ManyToOne
	@JoinColumn(name = "clock_speed_unit_id", nullable = true)
	private Unit clockSpeedUnit;
	
	@OneToOne(mappedBy = "cpu")
	private Laptop laptop;
	@OneToOne(mappedBy = "cpu")
	private Pc pc;
	
	public Cpu() {}
	
	public Cpu(Long id) {
		this.id = id;
	}
	
	public Cpu(CpuRequest req) {
		this.manufacture = new Manufacture(req.manufactureId());
		this.model = req.model();
		this.threadCount = req.threadCount();
		this.clockSpeed = req.clockSpeed();
		this.clockSpeedUnit = new Unit(req.clockSpeedUnitId());
	}
	
	public Cpu(Long id, CpuRequest req) {
		this.id = id;
		this.manufacture = new Manufacture(req.manufactureId());
		this.model = req.model();
		this.threadCount = req.threadCount();
		this.clockSpeed = req.clockSpeed();
		this.clockSpeedUnit = new Unit(req.clockSpeedUnitId());
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
	public Short getThreadCount() {
		return threadCount;
	}
	public void setThreadCount(Short threadCount) {
		this.threadCount = threadCount;
	}
	public Double getClockSpeed() {
		return clockSpeed;
	}
	public void setClockSpeed(Double clockSpeed) {
		this.clockSpeed = clockSpeed;
	}
	public Unit getClockSpeedUnit() {
		return clockSpeedUnit;
	}
	public void setClockSpeedUnit(Unit clockSpeedUnit) {
		this.clockSpeedUnit = clockSpeedUnit;
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
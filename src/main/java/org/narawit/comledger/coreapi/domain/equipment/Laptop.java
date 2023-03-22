package org.narawit.comledger.coreapi.domain.equipment;

import org.narawit.comledger.coreapi.contracts.equipment.LaptopRequest;
import org.narawit.comledger.coreapi.domain.Equipment;
import org.narawit.comledger.coreapi.domain.option.CdromType;
import org.narawit.comledger.coreapi.domain.option.Manufacture;

import jakarta.persistence.CascadeType;
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
@Table(name = "laptop", schema = "equipment_detail", indexes = {
		@Index(columnList = "id", name = "laptop_pkey_idx"),
		@Index(columnList = "equipment_id", name = "laptop_eq_fk_idx")
})
public class Laptop {
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
	
	@Column(length = 30, nullable = false)
	private String model;
	@Column(name = "pointer_device", length = 30)
	private String pointerDevice;
	@Column(length = 30)
	private String keyboard;
	
	@ManyToOne
	@JoinColumn(name = "cdrom_type_id", nullable = true)
	private CdromType cdromType;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cpu_id", referencedColumnName = "id")
	private Cpu cpu;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ram_id", referencedColumnName = "id")
	private Ram ram;
	
	public Laptop() {}
	
	public Laptop(Long id) {
		this.id = id;
	}
	
	public Laptop(LaptopRequest req) {
		this.equipment = new Equipment(req.equipmentId());
		this.manufacture = new Manufacture(req.manufactureId());
		this.model = req.model();
		this.pointerDevice = req.pointerDevice();
		this.keyboard = req.keyboard();
		this.cdromType = new CdromType(req.cdRomTypeId());
		this.cpu = new Cpu(req.cpuId());
		this.ram = new Ram(req.ramId());
	}
	
	public Laptop(Long id, LaptopRequest req) {
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

	public String getPointerDevice() {
		return pointerDevice;
	}

	public void setPointerDevice(String pointerDevice) {
		this.pointerDevice = pointerDevice;
	}

	public String getKeyboard() {
		return keyboard;
	}

	public void setKeyboard(String keyboard) {
		this.keyboard = keyboard;
	}

	public CdromType getCdromType() {
		return cdromType;
	}

	public void setCdromType(CdromType cdromType) {
		this.cdromType = cdromType;
	}

	public Cpu getCpu() {
		return cpu;
	}

	public void setCpu(Cpu cpu) {
		this.cpu = cpu;
	}

	public Ram getRam() {
		return ram;
	}

	public void setRam(Ram ram) {
		this.ram = ram;
	}
}

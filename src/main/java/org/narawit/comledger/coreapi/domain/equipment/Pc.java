package org.narawit.comledger.coreapi.domain.equipment;

import org.narawit.comledger.coreapi.contracts.equipment.PcRequest;
import org.narawit.comledger.coreapi.domain.Equipment;
import org.narawit.comledger.coreapi.domain.option.CdromType;
import org.narawit.comledger.coreapi.domain.option.Manufacture;
import org.narawit.comledger.coreapi.domain.option.Unit;

import jakarta.persistence.CascadeType;
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
@Table(name = "pc", schema = "equipment_detail", indexes = {
		@Index(columnList = "id", name = "pc_pkey_idx"),
		@Index(columnList = "equipment_id", name = "pc_eq_fk_idx")
})
public class Pc {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "equipment_id", nullable = false)
	private Equipment equipment;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mb_manufacture_id", nullable = false)
	private Manufacture mbManufacture;
	@Column(name = "mb_model", length = 30, nullable = false)
	private String mbModel;
	@Column(name = "pointer_device", length = 30)
	private String pointerDevice;
	@Column(length = 30, nullable = false)
	private String keyboard;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "cpu_id", referencedColumnName = "id")
	private Cpu cpu;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ram_id", referencedColumnName = "id")
	private Ram ram;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cdrom_type_id", referencedColumnName = "id", nullable = true)
	private CdromType cdromType;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "psu_manufacture_id", referencedColumnName = "id", nullable = true)
	private Manufacture psuManufacture;
	@Column(name = "psu_power", nullable = true)
	private Double psuPower;
	@ManyToOne
	@JoinColumn(name = "psu_unit_id", nullable = true)
	private Unit psuUnit;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nic_id", nullable = true)
	private Nic nic;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ups_id", nullable = true)
	private Ups ups;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vga_id", nullable = true)
	private Vga vga;
	
	public Pc() {}
	
	public Pc(Long identity) {
		this.id = identity;
	}
	
	public Pc(PcRequest req) {
		this.equipment = new Equipment(req.equipmentId());
		this.mbManufacture = new Manufacture(req.mainboardManufactureid());
		this.mbModel = req.mainboardModel();
		this.pointerDevice = req.pointerDevice();
		this.keyboard = req.keyboard();
		this.cpu = new Cpu(req.cpuId());
		this.ram = new Ram(req.ramId());
		this.cdromType = new CdromType(req.cdromTypeId());
		this.psuManufacture = new Manufacture(req.psuManufactureId());
		this.psuPower = req.psuPower();
		this.psuUnit = new Unit(req.psuUnitId());
		this.ups = new Ups(req.upsId());
		this.nic = new Nic(req.nicId());
		this.vga = new Vga(req.vgaId());
	}
	
	public Pc(Long identity, PcRequest req) {
		this(req);
		this.id = identity;
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

	public Manufacture getMbManufacture() {
		return mbManufacture;
	}

	public void setMbManufacture(Manufacture mbManufacture) {
		this.mbManufacture = mbManufacture;
	}

	public String getMbModel() {
		return mbModel;
	}

	public void setMbModel(String mbModel) {
		this.mbModel = mbModel;
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

	public CdromType getCdromType() {
		return cdromType;
	}

	public void setCdromType(CdromType cdromTypeId) {
		this.cdromType = cdromTypeId;
	}

	public Manufacture getPsuManufacture() {
		return psuManufacture;
	}

	public void setPsuManufacture(Manufacture psuManufacture) {
		this.psuManufacture = psuManufacture;
	}

	public Double getPsuPower() {
		return psuPower;
	}

	public void setPsuPower(Double psuPower) {
		this.psuPower = psuPower;
	}

	public Unit getPsuUnit() {
		return psuUnit;
	}

	public void setPsuUnit(Unit psuUnit) {
		this.psuUnit = psuUnit;
	}

	public Nic getNic() {
		return nic;
	}

	public void setNic(Nic nic) {
		this.nic = nic;
	}

	public Ups getUps() {
		return ups;
	}

	public void setUps(Ups ups) {
		this.ups = ups;
	}

	public Vga getVga() {
		return vga;
	}

	public void setVga(Vga vga) {
		this.vga = vga;
	}
}

package org.narawit.comledger.coreapi.domain.equipment;

import java.time.ZonedDateTime;

import org.narawit.comledger.coreapi.contracts.equipment.PrinterRequest;
import org.narawit.comledger.coreapi.domain.Equipment;
import org.narawit.comledger.coreapi.domain.option.Manufacture;
import org.narawit.comledger.coreapi.domain.option.PrinterType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "printer", schema = "equipment_detail", indexes = {
		@Index(columnList = "id", name = "printer_pkey_idx"),
		@Index(columnList = "equipment_id", name = "printer_eq_fk_idx")
})
public class Printer {
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
	@ManyToOne
	@JoinColumn(name = "printer_type_id", nullable = false)
	private PrinterType printerType;
	@Column(length = 20, nullable = false)
	private String model;
	@Column(name = "ink_model", length = 30, nullable = false)
	private String inkModel;
	@Column(name = "last_ink_reload_date")
	private ZonedDateTime lastInkReloadDate;
	
	public Printer() {}
	
	public Printer(PrinterRequest req) {
		this.equipment = new Equipment(req.equipmentId());
		this.manufacture = new Manufacture(req.manufactureId());
		this.printerType = new PrinterType(req.printerTypeId());
		this.model = req.model();
		this.inkModel = req.inkModel();
		this.lastInkReloadDate = req.lastInkReloadDate();
	}
	
	public Printer(Long id, PrinterRequest req) {
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

	public PrinterType getPrinterType() {
		return printerType;
	}

	public void setPrinterType(PrinterType printerType) {
		this.printerType = printerType;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getInkModel() {
		return inkModel;
	}

	public void setInkModel(String inkModel) {
		this.inkModel = inkModel;
	}

	public ZonedDateTime getLastInkReloadDate() {
		return lastInkReloadDate;
	}

	public void setLastInkReloadDate(ZonedDateTime lastInkReloadDate) {
		this.lastInkReloadDate = lastInkReloadDate;
	}
}

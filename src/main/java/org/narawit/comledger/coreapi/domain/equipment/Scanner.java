package org.narawit.comledger.coreapi.domain.equipment;

import org.narawit.comledger.coreapi.contracts.equipment.ScannerRequest;
import org.narawit.comledger.coreapi.domain.Equipment;
import org.narawit.comledger.coreapi.domain.option.Manufacture;

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
@Table(name = "scanner", schema = "equipment_detail", indexes = {
		@Index(columnList = "id", name = "scanner_pkey_idx"),
		@Index(columnList = "equipment_id", name = "scanner_eq_fk_idx")
})
public class Scanner {
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
	@Column(length = 20, nullable = false)
	private String model;
	
	public Scanner() {}
	
	public Scanner(Long id) {
		this.id = id;
	}
	
	public Scanner(ScannerRequest req) {
		this.equipment = new Equipment(req.equipmentId());
		this.manufacture = new Manufacture(req.manufactureId());
		this.model = req.model();
	}
	
	public Scanner(Long id, ScannerRequest req) {
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
}

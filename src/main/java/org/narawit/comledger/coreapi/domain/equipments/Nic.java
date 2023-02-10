package org.narawit.comledger.coreapi.domain.equipments;

import org.narawit.comledger.coreapi.domain.Equipment;
import org.narawit.comledger.coreapi.domain.option.Manufacture;
import org.narawit.comledger.coreapi.domain.option.NetworkConnectionType;
import org.narawit.comledger.coreapi.domain.option.NetworkConnectorType;
import org.narawit.comledger.coreapi.domain.option.Unit;

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
@Table(name = "nic", schema = "equipment_detail", indexes = {
		@Index(columnList = "id", name = "nic_pkey_idx"),
		@Index(columnList = "equipment_id", name = "nic_eq_fk_idx")
})
public class Nic {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "equipment_id", nullable = false)
	private Equipment equipment;
	/*
	@ManyToOne
	@JoinColumn(name = "pc_id",  nullable = true)
	private Pc pc;
	*/
	@ManyToOne
	@JoinColumn(name = "manufacture_id",  nullable = false)
	private Manufacture manufacture;
	@ManyToOne
	@JoinColumn(name = "connection_type_id", nullable = false)
	private NetworkConnectionType connectionType;
	@ManyToOne
	@JoinColumn(name = "connector_type_id", nullable = false)
	private NetworkConnectorType connectorType;
	@Column(length = 20, nullable = false)
	private String model;
	@Column(nullable = false)
	private Double speed;
	@ManyToOne
	@JoinColumn(name = "speed_unit_id", nullable = false)
	private Unit speedUnit;
	
	@OneToOne(mappedBy = "nic")
	private Pc pc;
	
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

	public NetworkConnectionType getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(NetworkConnectionType connectionType) {
		this.connectionType = connectionType;
	}

	public NetworkConnectorType getConnectorType() {
		return connectorType;
	}

	public void setConnectorType(NetworkConnectorType connectorType) {
		this.connectorType = connectorType;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public Unit getSpeedUnit() {
		return speedUnit;
	}

	public void setSpeedUnit(Unit speedUnit) {
		this.speedUnit = speedUnit;
	}

	public Nic() {}
}
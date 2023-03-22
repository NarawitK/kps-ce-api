package org.narawit.comledger.coreapi.domain;

import java.time.ZonedDateTime;

import org.narawit.comledger.coreapi.contract.MaintenanceRequest;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "maintenance_history", schema = "public", indexes = {
		@Index(columnList = "id", name = "maintenance_pkey_idx"),
		@Index(columnList = "equipment_id", name = "maintenance_equip_fk_idx"),
		@Index(columnList = "done", name = "maintenance_done_idx")
})
public class MaintenanceHistory {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 500, nullable = false)
	private String detail;
	@Column(name = "created_at", 
			columnDefinition = "timestamp with time zone DEFAULT now()", 
			nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private ZonedDateTime createdAt;
	@Column(columnDefinition = "boolean DEFAULT false", 
			nullable = false)
	private boolean done;
	@Column(name = "done_date")
	@Temporal(TemporalType.TIMESTAMP)
	private ZonedDateTime doneDate;
	
	// Foreign Key from other tables
	@ManyToOne
	@JoinColumn(name = "equipment_id", nullable = false)
	private Equipment equipment;
	@ManyToOne
	@JoinColumn(name = "created_by", nullable = false)
	private User createdByUser;
	
	public MaintenanceHistory() {}
	
	public MaintenanceHistory(MaintenanceRequest req) {
		this.detail = req.detail();
		this.done = req.done();
		this.doneDate = req.doneDate();
	}
	
	public MaintenanceHistory(Long id, MaintenanceRequest req) {
		this.id = id;
		this.detail = req.detail();
		this.done = req.done();
		this.doneDate = req.doneDate();
		this.equipment = new Equipment(req.equipmentId());
		this.createdByUser = new User(req.userId());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public ZonedDateTime getDoneDate() {
		return doneDate;
	}

	public void setDoneDate(ZonedDateTime doneDate) {
		this.doneDate = doneDate;
	}

	public User getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
	}
}

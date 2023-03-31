package org.narawit.comledger.coreapi.domain;

import java.time.ZonedDateTime;

import org.narawit.comledger.coreapi.contract.EventLogRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "event_log", schema = "public", indexes = {
		@Index(columnList = "id", name = "event_log_pkey_idx"),
		@Index(columnList = "log_date", name = "event_log_logdate_idx"),
		@Index(columnList = "operation", name = "event_log_ops_idx")
})
public class EventLog {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "log_date",
			columnDefinition = "timestamp with time zone DEFAULT NOW()", 
			nullable = false,
			updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private ZonedDateTime logDate;
	@Column(length = 10, nullable = false)
	private String operation;
	@Column(nullable = false)
	private String header;
	private String detail;
	
	public EventLog() {}
	
	public EventLog(Long identity, EventLogRequest req) {
		this(req);
		this.id = identity;
	}
	
	public EventLog(EventLogRequest req) {
		this.logDate = req.logdate();
		this.operation = req.operation();
		this.header = req.header();
		this.detail = req.detail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ZonedDateTime getLogDate() {
		return logDate;
	}

	public void setLogDate(ZonedDateTime logDate) {
		this.logDate = logDate;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}

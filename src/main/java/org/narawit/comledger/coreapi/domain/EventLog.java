package org.narawit.comledger.coreapi.domain;

import java.time.ZonedDateTime;

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
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eventlog_id_generator")
	// @SequenceGenerator(name = "eventlog_id_generator", sequenceName = "ev_log_id_seq", allocationSize = 1)
	private long id;
	@Column(name = "log_date", 
			columnDefinition = "timestamp with time zone DEFAULT NOW()", 
			nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private ZonedDateTime logDate;
	@Column(length = 10, nullable = false)
	private String operation;
	@Column(nullable = false)
	private String header;
	private String detail;
	
	public EventLog(long id, ZonedDateTime logDate, String operation, String header, String detail) {
		super();
		this.id = id;
		this.logDate = logDate;
		this.operation = operation;
		this.header = header;
		this.detail = detail;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

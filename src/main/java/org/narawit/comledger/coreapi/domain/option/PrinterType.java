package org.narawit.comledger.coreapi.domain.option;

import java.util.Set;

import org.narawit.comledger.coreapi.contract.option.PrinterTypeRequest;
import org.narawit.comledger.coreapi.domain.equipment.Printer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "printer_type", schema = "option", indexes = @Index(columnList = "id", name = "printer_type_pkey_idx"))
public class PrinterType {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 40, nullable = false)
	private String type;
	
	@OneToMany(mappedBy = "printerType", fetch = FetchType.LAZY)
	private Set<Printer> printers;
	
	public PrinterType() {}
	
	public PrinterType(Integer id) {
		this.id = id;
	}
	
	public PrinterType(PrinterTypeRequest req) {
		this.type = req.type();
	}
	
	public PrinterType(Integer id, PrinterTypeRequest req) {
		this(id);
		this.type = req.type();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Printer> getPrinters() {
		return printers;
	}

	public void setPrinters(Set<Printer> printers) {
		this.printers = printers;
	}
}

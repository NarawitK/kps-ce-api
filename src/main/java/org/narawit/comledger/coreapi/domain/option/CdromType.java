package org.narawit.comledger.coreapi.domain.option;

import java.util.Set;

import org.narawit.comledger.coreapi.contract.option.CdromTypeRequest;
import org.narawit.comledger.coreapi.domain.equipment.Pc;

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
@Table(name = "cdrom_type", schema = "option", indexes = @Index(columnList = "id", name = "cdrom_type_pkey_idx"))
public class CdromType {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 10, nullable = false)
	private String type;
	
	@OneToMany(mappedBy = "cdromType", fetch = FetchType.LAZY)
	private Set<Pc> pcs;
	
	public CdromType() {}
	
	public CdromType(Integer id) {
		this.id = id;
	}
	
	public CdromType(CdromTypeRequest req) {
		this.type = req.type();
	}
	
	public CdromType(Integer id, CdromTypeRequest req) {
		this.id = id;
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

	public Set<Pc> getPcs() {
		return pcs;
	}

	public void setPcs(Set<Pc> pcs) {
		this.pcs = pcs;
	}
}

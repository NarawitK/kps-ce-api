package org.narawit.comledger.coreapi.domain.option;

import java.util.Set;

import org.narawit.comledger.coreapi.contract.option.UnitTypeRequest;

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
@Table(name = "unit_type", schema = "option", indexes = @Index(columnList = "id", name = "ut_pkey_idx"))
public class UnitType {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 20, nullable = false)
	private String type;
	
	// Referenced Key
	@OneToMany(mappedBy = "unitType", fetch = FetchType.LAZY)
	private Set<Unit> units;

	public UnitType() {}
	
	public UnitType(Integer id) {
		this.id = id;
	}
	
	public UnitType(UnitTypeRequest req) {
		this.type = req.type();
	}
	public UnitType(Integer id, UnitTypeRequest req) {
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

	public Set<Unit> getUnits() {
		return units;
	}

	public void setUnits(Set<Unit> units) {
		this.units = units;
	}
}

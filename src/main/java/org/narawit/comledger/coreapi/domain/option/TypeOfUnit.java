package org.narawit.comledger.coreapi.domain.option;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "typeof_unit", schema = "option", indexes = @Index(columnList = "id", name = "tou_pkey_idx"))
public class TypeOfUnit {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "type_of_unit_id_gen")
	// @SequenceGenerator(name = "type_of_unit_id_gen", sequenceName = "type_of_unit_id_seq", allocationSize = 1)
	private Integer id;
	@Column(length = 20, nullable = false)
	private String type;
	
	// References Key
	@OneToMany(mappedBy = "unitType")
	private Set<Unit> units;

	public TypeOfUnit() {}

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

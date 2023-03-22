package org.narawit.comledger.coreapi.domain.option;

import java.util.Set;

import org.narawit.comledger.coreapi.contract.option.ManufactureCatRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "manufacture_category", schema = "option", indexes = @Index(columnList = "id", name = "mcat_pkey_idx"))
public class ManufactureCategory {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 50, nullable = false)
	private String category;
	
	@OneToMany(mappedBy = "manufactureCategory")
	private Set<Manufacture> manufactures;
	
	public ManufactureCategory() {}
	
	public ManufactureCategory(Integer id) {
		this.id = id;
	}

	public ManufactureCategory(ManufactureCatRequest req) {
		this.category = req.category();
	}
	
	public ManufactureCategory(Integer id, ManufactureCatRequest req) {
		this.id = id;
		this.category = req.category();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Set<Manufacture> getManufactures() {
		return manufactures;
	}

	public void setManufactures(Set<Manufacture> manufactures) {
		this.manufactures = manufactures;
	}
}

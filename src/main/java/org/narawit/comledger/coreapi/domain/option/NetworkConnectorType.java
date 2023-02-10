package org.narawit.comledger.coreapi.domain.option;

import java.util.Set;

import org.narawit.comledger.coreapi.domain.equipments.Nic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "network_connector_type", schema = "option", indexes = @Index(columnList = "id", name = "netcon_pkey_idx"))
public class NetworkConnectorType {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "netconn_id_gen")
	// @SequenceGenerator(name = "netconn_id_gen", sequenceName = "netconn_id_seq", allocationSize = 1)
	private Integer id;
	@Column(length = 20, nullable = false)
	private String connector;
	
	@OneToMany(mappedBy = "connectorType")
	public Set<Nic> nics;
	
	public NetworkConnectorType() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConnector() {
		return connector;
	}

	public void setConnector(String connector) {
		this.connector = connector;
	}

	public Set<Nic> getNics() {
		return nics;
	}

	public void setNics(Set<Nic> nics) {
		this.nics = nics;
	}
	
}

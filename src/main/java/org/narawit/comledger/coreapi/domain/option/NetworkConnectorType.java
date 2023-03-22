package org.narawit.comledger.coreapi.domain.option;

import java.util.Set;

import org.narawit.comledger.coreapi.contract.option.NetworkConnectorTypeRequest;
import org.narawit.comledger.coreapi.domain.equipment.Nic;

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
@Table(name = "network_connector_type", schema = "option", indexes = @Index(columnList = "id", name = "netcon_pkey_idx"))
public class NetworkConnectorType {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 20, nullable = false)
	private String connector;
	
	@OneToMany(mappedBy = "connectorType", fetch = FetchType.LAZY)
	public Set<Nic> nics;
	
	public NetworkConnectorType() {}
	
	public NetworkConnectorType(Integer id) {
		this.id = id;
	}
	public NetworkConnectorType(Integer id, NetworkConnectorTypeRequest req) {
		this.id = id;
		this.connector = req.connector();
	}
	public NetworkConnectorType(NetworkConnectorTypeRequest req) {
		this.connector = req.connector();
	}

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

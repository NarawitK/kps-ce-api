package org.narawit.comledger.coreapi.domain.option;

import java.util.Set;

import org.narawit.comledger.coreapi.contract.option.NetworkConnectionTypeRequest;
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
@Table(name = "network_connection_type", schema = "option", indexes = @Index(columnList = "id", name = "netconn_pkey_idx"))
public class NetworkConnectionType {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "connection_type", length = 20, nullable = false)
	private String connectionType;
	
	@OneToMany(mappedBy = "connectionType", fetch = FetchType.LAZY)
	public Set<Nic> nics;
	
	public NetworkConnectionType() {}
	
	public NetworkConnectionType(Integer id) {
		this.id = id;
	}
	
	public NetworkConnectionType(NetworkConnectionTypeRequest req) {
		this.connectionType = req.connectionType();
	}
	
	public NetworkConnectionType(Integer id, NetworkConnectionTypeRequest req) {
		this.id = id;
		this.connectionType = req.connectionType();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

	public Set<Nic> getNics() {
		return nics;
	}

	public void setNics(Set<Nic> nics) {
		this.nics = nics;
	}
}

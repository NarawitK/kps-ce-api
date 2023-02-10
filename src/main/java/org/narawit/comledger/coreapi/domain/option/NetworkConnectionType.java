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
@Table(name = "network_connection_type", schema = "option", indexes = @Index(columnList = "id", name = "netconn_pkey_idx"))
public class NetworkConnectionType {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "netcon_id_gen")
	// @SequenceGenerator(name = "netcon_id_gen", sequenceName = "netcon_id_seq", allocationSize = 1)
	private Integer id;
	@Column(name = "connection_type", length = 20, nullable = false)
	private String connectionType;
	
	@OneToMany(mappedBy = "connectionType")
	public Set<Nic> nics;
	
	public NetworkConnectionType() {}

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

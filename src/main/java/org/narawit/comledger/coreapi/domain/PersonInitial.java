package org.narawit.comledger.coreapi.domain;


import org.narawit.comledger.coreapi.contract.PersonInitialRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "person_initial", schema = "public", indexes = @Index(columnList = "id", name = "person_init_id_idx"))
public class PersonInitial {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 25, nullable = false)
	private String initial;
	
	// Reference Key in other tables
	@OneToOne(mappedBy = "initial", fetch = FetchType.LAZY)
	private User user;
	
	public PersonInitial() {}
	
	public PersonInitial(Long id) {
		this.id = id;
	}
	public PersonInitial(PersonInitialRequest req) {
		this.initial = req.initial();
	}
	public PersonInitial(Long id, PersonInitialRequest req) {
		this.id = id;
		this.initial = req.initial();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getInitial() {
		return initial;
	}
	
	public void setInitial(String initial) {
		this.initial = initial;
	}
	public User getUsers(){
		return this.user;
	}
	public void setUsers(User user) {
		this.user = user;
	}
}

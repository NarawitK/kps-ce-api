package org.narawit.comledger.coreapi.domain;

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
@Table(name = "person_initial", schema = "public", indexes = @Index(columnList = "id", name = "person_init_id_idx"))
public class PersonInitial {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "initial_id_gen")
	// @SequenceGenerator(name = "initial_id_gen", sequenceName = "initial_id_seq", allocationSize = 1)
	private Long id;
	@Column(length = 25, nullable = false)
	private String initial;
	
	// Reference Key Declaration
	@OneToMany(mappedBy = "initial")
	private Set<User> users;
	
	public PersonInitial() {}
	public PersonInitial(String initial) {
		this.initial = initial;
	}
	public PersonInitial(long id, String initial) {
		this.id = id;
		this.initial = initial;
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
	public Set<User> getUsers(){
		return this.users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
}

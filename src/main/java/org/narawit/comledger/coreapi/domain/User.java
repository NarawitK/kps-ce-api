package org.narawit.comledger.coreapi.domain;

import java.time.ZonedDateTime;
import java.util.Set;

import org.narawit.comledger.coreapi.contract.UserRequest;

import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "user", schema = "public", 
indexes = {
		@Index(columnList = "id", name = "user_id_idx"),
		@Index(columnList = "initial_id", name = "user_fk_idx"),
		@Index(columnList = "username", name = "username_idx")
}, 
uniqueConstraints = {
		@UniqueConstraint(columnNames = "email", name = "user_email_unique"),
		@UniqueConstraint(columnNames = "username", name = "username_unique")
})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "initial_id", nullable = false)
	private PersonInitial initial;
	@Column(length = 40, nullable = false)
	private String username;
	@Column(length = 100, nullable = false)
	private String firstname;
	@Column(length = 100, nullable = false)
	private String lastname;
	@Column(length = 150)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(name = "last_login")
	@Temporal(TemporalType.TIMESTAMP)
	private ZonedDateTime lastLogin;
	@Column(name = "active", columnDefinition = "boolean DEFAULT true", nullable = false)
	private boolean active;
	
	@OneToMany(mappedBy = "createdByUser", fetch = FetchType.LAZY)
	private Set<MaintenanceHistory> maintenanceHistories;
	
	public User() {}
	
	public User(Long id) {
		this.id = id;
	}
	
	public User(UserRequest req) {
		this.initial = new PersonInitial(req.initialId());
		this.firstname = req.firstname();
		this.lastname = req.lastname();
		this.email = req.email();
		this.username = req.username();
		this.password = req.password();
		this.active = req.active();
	}
	
	public User(Long id, UserRequest req) {
		this.id = id;
		this.initial = new PersonInitial(req.initialId());
		this.firstname = req.firstname();
		this.lastname = req.lastname();
		this.email = req.email();
		this.username = req.username();
		this.password = req.password();
		this.active = req.active();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonInitial getInitial() {
		return initial;
	}

	public void setInitial(PersonInitial initial) {
		this.initial = initial;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ZonedDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(ZonedDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean isActive) {
		this.active = isActive;
	}
}

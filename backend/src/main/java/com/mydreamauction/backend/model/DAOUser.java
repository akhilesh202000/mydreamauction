package com.mydreamauction.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "user")

public class DAOUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String username;

	@Column
	private String role;

	@Column
	@JsonIgnore
	private String password;

	@Column
	private boolean isLocked;

	@Column
	private String email;

	@Column
	private String roleFunction;

	public String getUsername() {
		return username;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	public boolean getLocked() {
		return isLocked;
	}

	public void setLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoleFunction() {
		return roleFunction;
	}

	public void setRoleFunction(String roleFunction) {
		this.roleFunction = roleFunction;
	}
}
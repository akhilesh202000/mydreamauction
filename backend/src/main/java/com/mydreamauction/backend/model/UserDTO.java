package com.mydreamauction.backend.model;

public class UserDTO {
	private String username;
	private String password;

	private String role;

	private boolean isLocked;

	private String email;

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


	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getRoleFunction() {
		return roleFunction;
	}

	public void setRoleFunction(String roleFunction) {
		this.roleFunction = roleFunction;
	}

}
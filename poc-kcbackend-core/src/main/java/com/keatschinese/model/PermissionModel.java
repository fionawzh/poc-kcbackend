package com.keatschinese.model;

import org.springframework.data.annotation.Id;

public class PermissionModel {

	@Id
	private String id;
	private String name;
	private boolean manageable;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isManageable() {
		return manageable;
	}

	public void setManageable(boolean manageable) {
		this.manageable = manageable;
	}
}

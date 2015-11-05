package com.keatschinese.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class RoleModel {

	@Id
	private String id;
	private String name;
	private boolean manageable;
	@DBRef
	private List<PermissionModel> permissions;
	
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
	public List<PermissionModel> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<PermissionModel> permissions) {
		this.permissions = permissions;
	}
	public boolean isManageable() {
		return manageable;
	}
	public void setManageable(boolean manageable) {
		this.manageable = manageable;
	}
}

package com.HFTest.service;

import java.util.List;

import com.HFTest.entities.Role;

public interface RoleService {

	public void addRole(Role role);
	public Role getRoleById(String id);
	public Role getRole(String rolename);
	public void updateRole(Role role);
	public boolean deleteRole(String id);
	public List<Role> getRoles();
}

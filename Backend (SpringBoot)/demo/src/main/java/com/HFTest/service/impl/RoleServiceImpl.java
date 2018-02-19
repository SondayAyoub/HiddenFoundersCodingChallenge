package com.HFTest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.HFTest.dao.RoleRepository;
import com.HFTest.entities.Role;
import com.HFTest.service.RoleService;

public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public void addRole(Role role) {
		roleRepository.save(role);
	}

	@Override
	public Role getRoleById(String id) {
		return roleRepository.findOne(id);
	}

	@Override
	public Role getRole(String rolename) {
		return roleRepository.findByName(rolename);
	}

	@Override
	public void updateRole(Role role) {
		Role update_role = roleRepository.findOne(role.getId());
		update_role.setName(role.getName());
		roleRepository.save(update_role);
		
	}

	@Override
	public boolean deleteRole(String id) {
		Role r = getRoleById(id);
		try {
			if (r != null) {
				roleRepository.delete(r);
				return true;
			}else
				return false; 
		} catch (Exception e) {
			System.out.println("Role does not exist!");
			return false;
		}
	}

	@Override
	public List<Role> getRoles() {
		return roleRepository.findAll();
	}

}

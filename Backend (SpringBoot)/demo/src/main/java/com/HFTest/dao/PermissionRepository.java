package com.HFTest.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.HFTest.entities.Permission;

@Repository
public interface PermissionRepository extends MongoRepository<Permission, String>{
	
	public Permission findByName(String name);
}

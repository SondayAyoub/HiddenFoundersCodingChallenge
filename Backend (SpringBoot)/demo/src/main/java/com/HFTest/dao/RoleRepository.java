package com.HFTest.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.HFTest.entities.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, String>{

	public Role findByName(String name);
}

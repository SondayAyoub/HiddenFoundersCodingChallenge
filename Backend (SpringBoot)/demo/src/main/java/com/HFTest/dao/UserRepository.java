package com.HFTest.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.HFTest.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

	public User findByEmail(String email);
}

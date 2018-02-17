package com.HFTest.dao;

import java.util.List;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.HFTest.entities.Shops;

@Repository
public interface ShopsRepository extends MongoRepository<Shops, String> {
	public Shops findById(String Id);
	public List<Shops> findByName(String name);
	public List<Shops> findByLocationNear(Point point);
}
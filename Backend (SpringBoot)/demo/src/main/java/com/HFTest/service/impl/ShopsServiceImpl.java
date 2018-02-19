package com.HFTest.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HFTest.dao.ShopsRepository;
import com.HFTest.dao.UserRepository;
import com.HFTest.entities.Shops;
import com.HFTest.entities.User;
import com.HFTest.service.ShopsService;

@Service
@Transactional
public class ShopsServiceImpl implements ShopsService {

	@Autowired
	private ShopsRepository shopsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public void addShop(Shops shop) {
		shopsRepository.save(shop);
	}

	@Override
	public Shops getShopsbyId(String shopId) {
		return shopsRepository.findById(shopId);
	}

	@Override
	public List<Shops> getShop(String name) {
		return shopsRepository.findByName(name);
	}

	@Override
	public void updateShops(String id, Shops shop) {
		shop.setId(id);
		shopsRepository.save(shop);
	}

	@Override
	public boolean deleteShops(String shopId) {
		Shops s = getShopsbyId(shopId);
		try {
			if (s != null) {
				shopsRepository.delete(s);
				return true;
			}else
				return false; 
		} catch (Exception e) {
			System.out.println("Shop does not exist!");
			return false;
		}	
	}

	@Override
	public List<Shops> getShops() {
		return shopsRepository.findAll();
	}

	@Override
	public Set<Shops> getShopsByUser(User user) {
		return userRepository.findByEmail(user.getEmail()).getFavShops();
	}

	@Override
	public List<Shops> getNearbyShops(Point point) {
		return shopsRepository.findByLocationNear(point);
	}

}

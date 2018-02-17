package com.HFTest.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.geo.Point;

import com.HFTest.entities.Shops;
import com.HFTest.entities.User;

public interface ShopsService {

	public void addShop(Shops shop);
	public Shops getShopsbyId(String shopId);
	public List<Shops> getShop(String name);
	public void updateShops(String Id, Shops shop);
	public boolean deleteShops(String shopId);
	public List<Shops> getShops();
	public Set<Shops> getShopsByUser(User user);
	public List<Shops> getNearbyShops(Point point);
}

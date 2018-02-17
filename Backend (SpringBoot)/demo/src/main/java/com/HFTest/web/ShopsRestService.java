package com.HFTest.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HFTest.entities.Shops;
import com.HFTest.service.ShopsService;

@RestController
@CrossOrigin("*")
public class ShopsRestService {

	@Autowired
	private ShopsService shopService;

	@RequestMapping(value="/shops", method=RequestMethod.GET)
	public List<Shops> getShops(){
		return shopService.getShops();
	}
	
	@RequestMapping(value="/nearbyShops", method=RequestMethod.GET)
	public List<Shops> getNearbyShops(@RequestParam(defaultValue="-6.843299") String p1, @RequestParam(defaultValue="33.970437") String p2) {
		System.out.println(shopService.getNearbyShops(new Point(Double.valueOf(p1), Double.valueOf(p2))).size());
		return shopService.getNearbyShops(new Point(Double.valueOf(p1), Double.valueOf(p2)));
	}
	
	@RequestMapping(value="/shops/{id}", method=RequestMethod.GET)
	public Shops getShop(@PathVariable String id){
		return shopService.getShopsbyId(id);
	}
	
	@RequestMapping(value="/searchShop/{name}", method=RequestMethod.GET)
	public List<Shops> getByName(@PathVariable String name){
		return shopService.getShop(name);
	}
	
	@RequestMapping(value="/shops", method=RequestMethod.POST)
	public Shops addShop(@RequestBody Shops shop){
		shopService.addShop(shop);
		return shop;
	}
	
	@RequestMapping(value="/shops/{id}", method=RequestMethod.DELETE)
	public boolean deleteShop(@PathVariable String id){
		shopService.deleteShops(id);
		return true;
	}
	
	@RequestMapping(value="/shops/{id}", method=RequestMethod.PUT)
	public Shops updateShop(@PathVariable String id, @RequestBody Shops shop){
		shopService.updateShops(id, shop);
		return shop;
	}
}
